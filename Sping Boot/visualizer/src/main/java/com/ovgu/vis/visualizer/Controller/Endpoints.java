package com.ovgu.vis.visualizer.Controller;

import com.ovgu.vis.visualizer.DAO.Request.FilterRequestBody;
import com.ovgu.vis.visualizer.DTO.LegendList;
import com.ovgu.vis.visualizer.DAO.Response.Response;
import com.ovgu.vis.visualizer.Entity.PatientInfo;
import com.ovgu.vis.visualizer.Service.LegendDetailsService;
import com.ovgu.vis.visualizer.Service.PatientInfoService;
import com.ovgu.vis.visualizer.Service.PatientRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Response> getPatients(@RequestBody FilterRequestBody filterConditions) {
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

}

