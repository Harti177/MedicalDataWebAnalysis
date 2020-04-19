package com.ovgu.vis.visualizer.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class LegendDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String key;

    private String value;

    private String legend;

    public LegendDetails(){}

    public LegendDetails(String key, String value, String legend){
        this.key = key;
        this.value = value;
        this.legend = legend;
    }
}
