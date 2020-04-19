package com.ovgu.vis.visualizer.Service;

import com.ovgu.vis.visualizer.Entity.PatientInfo;
import com.ovgu.vis.visualizer.Repository.PatientInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PatientInfoService {

    @Autowired
    PatientInfoRepository patientInfoRepository;

    public void createPatientDetails(PatientInfo patientInfo) {
            patientInfoRepository.save(patientInfo);
    }

    public List<PatientInfo> getAll(){
        return patientInfoRepository.findAll();
    }

}
