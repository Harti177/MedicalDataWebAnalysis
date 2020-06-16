package com.ovgu.vis.visualizer.Controller;

import com.opencsv.exceptions.CsvException;
import com.ovgu.vis.visualizer.DAO.Request.FilterRequestBody;
import com.ovgu.vis.visualizer.DTO.LegendList;
import com.ovgu.vis.visualizer.DAO.Response.Response;
import com.ovgu.vis.visualizer.Entity.PatientInfo;
import com.ovgu.vis.visualizer.Service.FolderScan;
import com.ovgu.vis.visualizer.Service.GlobalRecord;
import com.ovgu.vis.visualizer.ServiceInterface.LegendDetailsService;
import com.ovgu.vis.visualizer.ServiceInterface.PatientInfoService;
import com.ovgu.vis.visualizer.ServiceInterface.PatientRecordService;
import com.ovgu.vis.visualizer.Service.ReadLegendFromCSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;
import com.ovgu.vis.visualizer.Service.CopyFiles;

@RestController
public class Endpoints {

    @Autowired
    private PatientInfoService patientInfoService;
    @Autowired
    private LegendDetailsService legendDetailsService;
    @Autowired
    private PatientRecordService patientRecordService;
    @Autowired
    private ReadLegendFromCSV readLegendFromCSV;
    @Autowired
    private FolderScan folderScan;
    @Autowired
    private CopyFiles copyFiles ;
    @Autowired
    private GlobalRecord globalRecord;


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

    @GetMapping(value = "/getImage",produces = MediaType.IMAGE_PNG_VALUE)
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity getPatientImage(@RequestParam String imagePath){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(patientRecordService.getImage(imagePath));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getCause().getMessage());
        }
    }

    @RequestMapping(path = "/refreshFolder", method = RequestMethod.POST)
    public ResponseEntity refreshFolder(@RequestParam String directoryPath) throws IOException {
        return folderScan.folderScan(directoryPath);
//        ResponseEntity response = folderScan.folderScan(directoryPath);
//        if(response == null)
//        {
//            return ResponseEntity.noContent().build();
//        }
//        return response;
    }

    //@RequestMapping(path ="/addLegend",method = RequestMethod.POST)
    //public void getLegendFromCsv(@RequestParam String directoryPath) throws IOException, CsvException {
     //   readLegendFromCSV.readLegendCSV(directoryPath);
    //}

    @RequestMapping(path="/copyFiles", method= RequestMethod.GET)
    public void collect3DFiles(@RequestParam String directoryPath, @RequestParam Boolean snapshot, @RequestParam Boolean threeDImage ) throws IOException {
        copyFiles.copy3DFiles(directoryPath,snapshot,threeDImage);
    }

    @RequestMapping(path ="/globalRecord", method = RequestMethod.GET)
    public void globalRecord(@RequestParam String directoryPath) throws IOException {
        globalRecord.collectAllRecords(directoryPath);
    }
}

