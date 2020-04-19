package com.ovgu.vis.visualizer.Service;

import com.ovgu.vis.visualizer.DTO.PatientRecord;
import com.ovgu.vis.visualizer.DTO.Patient;
import com.ovgu.vis.visualizer.Entity.PatientDetails;
import com.ovgu.vis.visualizer.Entity.PatientInfo;
import com.ovgu.vis.visualizer.Repository.LegendDetailsRepository;
import com.ovgu.vis.visualizer.Repository.PatientInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientRecordService {
    @Autowired
    private LegendDetailsRepository legendDetailsRepository;
    @Autowired
    private PatientInfoRepository patientInfoRepository;

    public List<Patient> getAllRecords(){
        List<Patient> patients = new ArrayList<>();
        List<PatientInfo> patientInfoList = patientInfoRepository.findAll();
        patientInfoList.forEach(patientInfo -> {
            List<PatientRecord> patientDetailsInRecords = new ArrayList<>();
            List<PatientDetails> patientDetails = patientInfo.getPatientDetails();
            patientDetails.forEach(patientDetail -> {
                patientDetailsInRecords.add(new PatientRecord(patientDetail.getKey(),patientDetail.getValue(),legendDetailsRepository.getJoinRecords(patientDetail.getKey(),patientDetail.getValue())));
            });
            patients.add(new Patient(patientInfo.getPatientId(),patientInfo.getInstitute(),patientInfo.getSex(),patientInfo.getAge(),patientInfo.getModality(),
                    patientInfo.getCreatedDate(),patientInfo.getThreeDimensionalImage(),patientInfo.getSnapshot(),patientDetailsInRecords));
        });
        return patients;
    }
}
