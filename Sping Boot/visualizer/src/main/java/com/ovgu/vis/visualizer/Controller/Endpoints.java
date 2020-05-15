package com.ovgu.vis.visualizer.Controller;

import com.ovgu.vis.visualizer.DAO.Request.FilterRequestBody;
import com.ovgu.vis.visualizer.DTO.LegendList;
import com.ovgu.vis.visualizer.Entity.LegendDetails;
import com.ovgu.vis.visualizer.ServiceInterface.LegendDetailsService;
import com.ovgu.vis.visualizer.ServiceInterface.PatientInfoService;
import com.ovgu.vis.visualizer.ServiceInterface.PatientRecordService;
import com.ovgu.vis.visualizer.Entity.PatientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Endpoints {

    @Autowired
    @Qualifier("patientInfo")
    private PatientInfoService patientInfoService;

    @Autowired
    @Qualifier("legendDetails")
    private LegendDetailsService legendDetailsService;

    @Autowired
    @Qualifier("patientRecords")
    private PatientRecordService patientRecordService;


    @GetMapping("/patients")
    @CrossOrigin("http://localhost:3000")
    public List<PatientInfo> fetchAll(){
        return patientInfoService.getAll();
    }

    @PostMapping ("/createPatient")
    @CrossOrigin("http://localhost:3000")
    public void create(@RequestBody PatientInfo patient){
        patientInfoService.createPatientDetails(patient);
    }

    @GetMapping("/legendsList")
    @CrossOrigin("http://localhost:3000")
    public List<LegendList> getAllLegends(){
        return  legendDetailsService.getAllLegends();
    }


    @PostMapping("/patientsRecords")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity getPatients(@RequestBody(required = false) FilterRequestBody filterConditions) {
        return patientRecordService.getPatients(filterConditions);
    }

    @PutMapping("/updateLegend")
    @CrossOrigin("http://localhost:3000")
    public void updateLegend(@RequestParam String key,@RequestParam String value, @RequestParam String legendName){
        legendDetailsService.updateLegend(key,value,legendName);
    }

    @DeleteMapping("/deleteLegend")
    @CrossOrigin("http://localhost:3000")
    public void deleteLegend(@RequestParam String key,@RequestParam String value){
        legendDetailsService.deleteLegend(key,value);
    }

    @PostMapping("/createLegend")
    @CrossOrigin("http://localhost:3000")
    public void createLegend(@RequestBody LegendDetails legendDetails){
        legendDetailsService.createLegends(legendDetails);
    }
}

