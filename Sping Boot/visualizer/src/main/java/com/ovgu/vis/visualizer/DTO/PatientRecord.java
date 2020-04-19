package com.ovgu.vis.visualizer.DTO;

import lombok.Data;

@Data
public class PatientRecord {

    private String key;

    private String value;

    public PatientRecord(String key, String value, String legend) {
        this.key = key;
        if(legend != null)
            this.value = legend;
        else
            this.value = value;
//            this.legend = legend;
    }
}
