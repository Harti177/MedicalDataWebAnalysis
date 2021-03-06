package com.ovgu.vis.visualizer.Service;

import com.ovgu.vis.visualizer.Entity.PatientInfo;
import com.ovgu.vis.visualizer.Repository.PatientInfoRepository;
import com.ovgu.vis.visualizer.ServiceInterface.PatientInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Qualifier("patientInfo")
class PatientInfoServiceImpl implements PatientInfoService {

    @Autowired
    PatientInfoRepository patientInfoRepository;

    @Override
    public void createPatientDetails(PatientInfo patientInfo) {
            patientInfoRepository.save(patientInfo);
    }

    @Override
    public List<PatientInfo> getAll(){
        return patientInfoRepository.findAll();
    }

}
