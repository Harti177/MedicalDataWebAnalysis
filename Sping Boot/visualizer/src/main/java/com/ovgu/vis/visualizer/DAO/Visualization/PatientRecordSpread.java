package com.ovgu.vis.visualizer.DAO.Visualization;

import com.ovgu.vis.visualizer.DAO.Response.PatientRecord;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PatientRecordSpread {


    Map<String, String> patientMap = new HashMap<String, String>();


    public PatientRecordSpread(String patientId, String institute, String sex, String age, List<PatientRecord> patientRecordList) {
        patientMap.put("patientId", patientId);
        patientMap.put("institute", institute);
        patientMap.put("sex", sex);
        patientMap.put("age", age);
        patientRecordList.forEach(patientRecord -> {
            patientMap.put(patientRecord.getKey(), patientRecord.getValue());
        });
    }

    public String getValue (String namedIndex){
        return patientMap.get(namedIndex);
    }
}
