package com.ovgu.vis.visualizer.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;


@Data
@AllArgsConstructor
@ToString
@Entity
public class PatientDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public int getRowNumber() {
        return rowNumber;
    }

    private int rowNumber;

    public String getCategory() {
        return category;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public long getId() {
        return id;
    }

    private String category;

    private String key;

    private String value;

    public PatientDetails() {
    }

    public PatientDetails(int rowNumber, String category, String key, String value) {
        this.rowNumber = rowNumber;
        this.category = category;
        this.key = key;
        this.value = value;
    }
}
