package com.ovgu.vis.visualizer.Service;

import com.ovgu.vis.visualizer.DTO.PatientRecord;
import com.ovgu.vis.visualizer.DTO.Patient;
import com.ovgu.vis.visualizer.DTO.Response;
import com.ovgu.vis.visualizer.Entity.PatientDetails;
import com.ovgu.vis.visualizer.Entity.PatientInfo;
import com.ovgu.vis.visualizer.Repository.LegendDetailsRepository;
import com.ovgu.vis.visualizer.Repository.PatientInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientRecordService {
    @Autowired
    private LegendDetailsRepository legendDetailsRepository;
    @Autowired
    private PatientInfoRepository patientInfoRepository;

    public Response getAllRecords(int pageNumber, int offset, String attribute, String sortBy){
        List<Patient> patients = new ArrayList<>();
        Enum sortDirection = sortBy.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Page<PatientInfo> patientInfoList = patientInfoRepository.findAll(PageRequest.of(pageNumber-1,offset, Sort.by(sortBy.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, attribute)));
        patientInfoList.forEach(patientInfo -> {
            List<PatientRecord> patientDetailsInRecords = new ArrayList<>();
            List<PatientDetails> patientDetails = patientInfo.getPatientDetails();
            patientDetails.forEach(patientDetail -> {
                patientDetailsInRecords.add(new PatientRecord(patientDetail.getKey(), patientDetail.getValue(), legendDetailsRepository.getJoinRecords(patientDetail.getKey(), patientDetail.getValue())));
            });
            patients.add(new Patient(patientInfo.getPatientId(),patientInfo.getInstitute(),patientInfo.getSex(),patientInfo.getAge(),patientInfo.getModality(),
                    patientInfo.getCreatedDate(),patientInfo.getThreeDimensionalImage(),patientInfo.getSnapshot(),patientDetailsInRecords));
        });
        return new Response(patientInfoList.getTotalElements(), patientInfoList.getPageable().getPageNumber()+1, patientInfoList.getTotalPages() , offset, patients);
    }
}
