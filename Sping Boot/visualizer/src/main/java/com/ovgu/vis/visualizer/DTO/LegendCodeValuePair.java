package com.ovgu.vis.visualizer.DTO;

import lombok.Data;

@Data
public class LegendCodeValuePair {

    private String code;

    private String value;

    public LegendCodeValuePair(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
