package com.ovgu.vis.visualizer.Service;

import com.opencsv.CSVWriter;
import com.ovgu.vis.visualizer.Entity.PatientInfo;
import com.ovgu.vis.visualizer.Repository.PatientInfoRepository;
import com.ovgu.vis.visualizer.Repository.PatientDetailsRepository;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


@Service
/*this creates a global record file of all the records present in the database
input : directory path = destination path where global record has to be saved*/
public class GlobalRecord {
    @Autowired
    PatientInfoRepository patientInfoRepository;
    @Autowired
    PatientDetailsRepository patientDetailsRepository;


    public void collectAllRecords(String directoryPath) throws IOException {
        List<PatientInfo> records = patientInfoRepository.findAll();
        List<String> b = new ArrayList<String>();
        ArrayList<String> output = new ArrayList<>();

        FileWriter fileWriter = new FileWriter(directoryPath + "\\Global_Records.csv");

        for (int i = 0; i <records.size(); i++) {
            b.add(records.get(i).getPatientId());
            b.add(records.get(i).getInstitute());
            b.add(records.get(i).getModality());
            b.add(records.get(i).getAge());
            b.add(records.get(i).getSex());

            //b.add(records.get(i).getSnapshot());
            //b.add(records.get(i).getThreeDimensionalImage());

            for (int j = 0; j < records.get(i).getPatientDetails().size(); j++) {
                b.add(records.get(i).getPatientDetails().get(j).getValue());
            }

            String collect = b.stream().collect(Collectors.joining(","));
            output.add(collect+"\n");
            b.clear();
        }

        StringBuilder filecontent = new StringBuilder("id,institution,modality,age,sex,aneurysmType,aneurysmLocation,ruptureStatus,multipleAneurysms,medicalHistory,Comment\n");

        for (String row : output) {
            filecontent.append(row);
        }

        fileWriter.write(filecontent.toString());
        fileWriter.flush();

    }
}
