package com.ovgu.vis.visualizer.Controller;

import com.ovgu.vis.visualizer.Entity.PatientDetails;
import com.ovgu.vis.visualizer.Entity.PatientInfo;
import com.ovgu.vis.visualizer.Repository.PatientInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Endpoints {

    @Autowired
    private PatientInfoRepository patientInfoRepository;

    @GetMapping("/patients")
    public List<PatientInfo> fetchAll(){
        return patientInfoRepository.findAll();
    }

    @PostMapping ("/create")
    public void create(@RequestBody PatientInfo patient){
        patientInfoRepository.save(patient);
    }

}

