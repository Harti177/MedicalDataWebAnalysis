package com.ovgu.vis.visualizer.Service;

import com.ovgu.vis.visualizer.Entity.PatientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.ovgu.vis.visualizer.Repository.PatientInfoRepository;
import com.ovgu.vis.visualizer.Repository.PatientDetailsRepository;

@Service
public class DeleteItemsFromDB   {

    @Autowired
    PatientInfoRepository patientInfoRepository;
    @Autowired
    PatientDetailsRepository patientDetailsRepository;


    public void deleteItemsFromDB() {
        patientInfoRepository.deleteAll();
        patientDetailsRepository.deleteAll();

    }


}