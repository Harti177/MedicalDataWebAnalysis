package com.ovgu.vis.visualizer.ServiceInterface;

import com.ovgu.vis.visualizer.Entity.PatientInfo;

import java.util.List;

public interface PatientInfoService {

   public void createPatientDetails(PatientInfo patientInfo);

    public List<PatientInfo> getAll();
}
