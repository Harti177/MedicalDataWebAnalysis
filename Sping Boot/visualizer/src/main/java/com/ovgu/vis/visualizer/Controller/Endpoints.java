package com.ovgu.vis.visualizer.Controller;

import com.ovgu.vis.visualizer.DTO.LegendList;
import com.ovgu.vis.visualizer.DTO.Patient;
import com.ovgu.vis.visualizer.Entity.PatientInfo;
import com.ovgu.vis.visualizer.Service.LegendDetailsService;
import com.ovgu.vis.visualizer.Service.PatientInfoService;
import com.ovgu.vis.visualizer.Service.PatientRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Endpoints {

    @Autowired
    private PatientInfoService patientInfoService;
    @Autowired
    private LegendDetailsService legendDetailsService;
    @Autowired
    private PatientRecordService patientRecordService;


    @GetMapping("/patients")
    public List<PatientInfo> fetchAll(){
        return patientInfoService.getAll();
    }

    @PostMapping ("/createPatient")
    public void create(@RequestBody PatientInfo patient){
        patientInfoService.createPatientDetails(patient);
    }

    @GetMapping("/legendsList")
    public List<LegendList> getAllLegends(){return  legendDetailsService.getAllLegends();}

    @GetMapping("/patientRecords")
    public List<Patient> getPatientRecords(){
        List<Patient> patients = patientRecordService.getAllRecords();
        return patients;
    }

}

