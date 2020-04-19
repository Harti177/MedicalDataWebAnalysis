package com.ovgu.vis.visualizer.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LegendList {

    private String key;


    private List<LegendCodeValuePair> legendCodeValuePairs;

    public LegendList(String key, List<LegendCodeValuePair> keyValues) {
        this.key = key;
        this.legendCodeValuePairs = keyValues;
    }
}
