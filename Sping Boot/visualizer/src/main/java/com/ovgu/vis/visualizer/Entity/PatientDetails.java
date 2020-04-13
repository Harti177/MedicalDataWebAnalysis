package com.ovgu.vis.visualizer.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Data
@AllArgsConstructor
@ToString
@Entity
public class PatientDetails {

    private static int incrementer = 0;

    @Id @GeneratedValue
    private long id;


    private int rowNumber;

    private String category;

    private String key;

    private String value;

    public PatientDetails() {

    }

    public PatientDetails(int rowNumber, String category, String key, String value) {
        this.id = incrementer++;
        this.rowNumber = rowNumber;
        this.category = category;
        this.key = key;
        this.value = value;
    }

}
