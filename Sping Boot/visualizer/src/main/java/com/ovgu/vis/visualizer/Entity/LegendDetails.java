package com.ovgu.vis.visualizer.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class LegendDetails {

    @Id @GeneratedValue
    private int id;

    private String key;

    private String value;

    private String legend;
}
