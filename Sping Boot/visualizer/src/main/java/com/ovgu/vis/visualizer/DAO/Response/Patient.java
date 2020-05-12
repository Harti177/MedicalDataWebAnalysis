package com.ovgu.vis.visualizer.DAO.Response;

import lombok.Data;

import java.util.List;


@Data
public class Patient {

    private String patientId;

    private String institute;

    private String sex;

    private String age;

    private String modality;

    private String createdDate;

    private String threeDimensionalImage;

    private String snapshot;

    private List<PatientRecord> patientRecords;


    public Patient(String patientId, String institute, String sex, String age, String modality, String createdDate, String threeDimensionalImage, String snapshot, List<PatientRecord> patientRecords) {
        this.patientId = patientId;
        this.institute = institute;
        this.sex = sex;
        this.age = age;
        this.modality = modality;
        this.createdDate = createdDate;
        this.threeDimensionalImage = threeDimensionalImage;
        this.snapshot = snapshot;
        this.patientRecords = patientRecords;
    }
}
