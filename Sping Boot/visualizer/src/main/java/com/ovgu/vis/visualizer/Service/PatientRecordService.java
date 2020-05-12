package com.ovgu.vis.visualizer.Service;

import com.ovgu.vis.visualizer.DAO.Request.Filter;
import com.ovgu.vis.visualizer.DAO.Request.FilterRequestBody;
import com.ovgu.vis.visualizer.DAO.Response.PatientRecord;
import com.ovgu.vis.visualizer.DAO.Response.Patient;
import com.ovgu.vis.visualizer.DAO.Response.Response;
import com.ovgu.vis.visualizer.Entity.PatientDetails;
import com.ovgu.vis.visualizer.Entity.PatientInfo;
import com.ovgu.vis.visualizer.Repository.LegendDetailsRepository;
import com.ovgu.vis.visualizer.Repository.PatientInfoRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PatientRecordService {
    @Autowired
    private LegendDetailsRepository legendDetailsRepository;
    @Autowired
    private PatientInfoRepository patientInfoRepository;
    @Autowired
    private EntityManager entityManager;

    private String queryBuilder_globalVariable = "";

    private String dynamicQueryBuilder_globalVariable = "";

    private boolean dateInFiltering_globalVariable = false;

    private String startDate_globalVariable = "";

    private String endDate_globalVariable = "";

    /*
     * Get the patient record with the provided parameters and filter constraints
     * */
    public ResponseEntity<Response> getPatients(FilterRequestBody conditions) {
        dateInFiltering_globalVariable = false;
        startDate_globalVariable = "";
        endDate_globalVariable = "";
        try{
        Session session = entityManager.unwrap(Session.class);

        //Apply filter to the query
        //queryBuilder = "select pi.id,pi.patientId,pi.institute,pi.sex,pi.age,pi.modality,pi.dateOfCreation,pi.threeDimensionalImage,pi.snapshot,pd.key,pd.value from PatientInfo pi join PatientDetails pd on pi.id = pd.patientInfoId and ";
        queryBuilder_globalVariable = "from PatientInfo pi where ";

        //call dynamic query buiilder for conditions specified in filter
        conditions.getFilters().forEach(filter -> {
            if(conditions.getFilters().indexOf(filter) != 0)
                queryBuilder_globalVariable += " and ";
            if(isPartOfMainQuery(filter.getFieldName())){
                if(filter.getFieldName().toLowerCase().equals("dateofcreation")){
                    dateInFiltering_globalVariable = true;
                    startDate_globalVariable = filter.getStart();
                    endDate_globalVariable = filter.getEnd();
                }
                queryBuilder_globalVariable += HQLSubQueryBuilder(filter, true);
            }
            else{           //to be queried from patientDetails
                String subQuery = HQLSubQueryBuilder(filter,false);
                queryBuilder_globalVariable += "pi.id in ( " + subQuery + " )";
            }
        });

        //pre-query to get the total entry
        String preQuery = "select count(pi.id) " + queryBuilder_globalVariable;
        Long totalEntries = dateInFiltering_globalVariable
                ?
                (Long) session.createQuery(preQuery).setParameter("startDate",startDate_globalVariable).setParameter("endDate",endDate_globalVariable).getSingleResult()    //if date is contained in query
                :
                (Long) session.createQuery(preQuery).getSingleResult();

        //addition of sorting attribute to the query
        String sortAttribute = conditions.getDisplayOrder().getFieldName().toLowerCase().equals("dateofcreation") ? "dateOfCreation" : conditions.getDisplayOrder().getFieldName().toLowerCase();
        String sortDirection = conditions.getDisplayOrder().getSortType().toLowerCase().equals("ascending") ? "ASC" : "DESC";
        queryBuilder_globalVariable += " order by pi." + sortAttribute + " " + sortDirection;

        //offset and limit
        int pageNumber = conditions.getPageInfo().getPageNumber();
        int recordsPerPage = conditions.getPageInfo().getNumberOfRecordsPerPage();

        //Main query
        List patientInfoCollection = dateInFiltering_globalVariable
                ?
                session.createQuery(queryBuilder_globalVariable).setFirstResult((pageNumber-1)*recordsPerPage).setMaxResults(recordsPerPage)
                        .setParameter("startDate",startDate_globalVariable).setParameter("endDate",endDate_globalVariable).list()   //if date is contained in query
                :
                session.createQuery(queryBuilder_globalVariable).setFirstResult((pageNumber-1)*recordsPerPage).setMaxResults(recordsPerPage).list();

        //fetching Legend details for each attribute
        List<Patient> patients = new ArrayList<>();
        patientInfoCollection.forEach(patientInfo -> {
            List<PatientRecord> patientDetailsInRecords = new ArrayList<>();
            List<PatientDetails> patientDetails = ((PatientInfo)patientInfo).getPatientDetails();
            patientDetails.forEach(patientDetail -> {
                patientDetailsInRecords.add(new PatientRecord(patientDetail.getKey(), patientDetail.getValue(), legendDetailsRepository.getJoinRecords(patientDetail.getKey(), patientDetail.getValue())));
            });
            patients.add(new Patient(((PatientInfo)patientInfo).getPatientId(),((PatientInfo)patientInfo).getInstitute(),((PatientInfo)patientInfo).getSex(),((PatientInfo)patientInfo).getAge(),((PatientInfo)patientInfo).getModality(),
                    ((PatientInfo)patientInfo).getDateOfCreation(),((PatientInfo)patientInfo).getThreeDimensionalImage(),((PatientInfo)patientInfo).getSnapshot(),patientDetailsInRecords));
        });

        return new ResponseEntity<>(new Response(totalEntries,conditions.getPageInfo().getPageNumber(), (int) Math.ceil((double)totalEntries/conditions.getPageInfo().getNumberOfRecordsPerPage()),
                conditions.getPageInfo().getNumberOfRecordsPerPage(),patients), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    /*
    * To identify whether to query patient info or patient details
    * */
    private boolean isPartOfMainQuery(String fieldName){
        return  (fieldName.toLowerCase().equals("age") || fieldName.toLowerCase().equals("institute") || fieldName.toLowerCase().equals("dateofcreation") ||
                fieldName.toLowerCase().equals("modality" ) || fieldName.toLowerCase().equals("sex"));
    }

    /*
    * To fetch the operator type
    * */
    private String getOperatorType(String type){
        switch (type.toLowerCase()){
            case "range":
                return "in";
            case "between":
                return "between";
            default:
                return "";
        }
    }

    /*
    * Dynamic Query builder
    * */
    private String HQLSubQueryBuilder(Filter condition, boolean mainQueryPart){
        String operator = getOperatorType(condition.getType());
        if(mainQueryPart){
            String attribute = condition.getFieldName().toLowerCase().equals("dateofcreation") ? "dateOfCreation" : condition.getFieldName().toLowerCase();
            dynamicQueryBuilder_globalVariable = "pi." + attribute + " " + getOperatorType(condition.getType());
        }
        else {
            dynamicQueryBuilder_globalVariable = "select pd.patientInfoId from PatientDetails pd where pd.key = '" + condition.getFieldName() +"' and pd.value "+ operator;
        }

        if(operator == "in") {
            dynamicQueryBuilder_globalVariable += " (";
            List<String> values = condition.getValues();
            values.forEach(value -> {
                dynamicQueryBuilder_globalVariable += values.indexOf(value) == 0 ? "'" + value + "'" : "," + "'" + value + "'";
            });
        }
        else if(operator == "between"){
            if(condition.getFieldName().toLowerCase().equals("dateofcreation")){
                dynamicQueryBuilder_globalVariable += " :startDate and :endDate";
            }
            else{
                dynamicQueryBuilder_globalVariable += " '" + condition.getStart() + "' and '" + condition.getEnd() + "'";
            }
        }
        if(!mainQueryPart){
            dynamicQueryBuilder_globalVariable += ")";
        }
        return dynamicQueryBuilder_globalVariable;
    }
}

