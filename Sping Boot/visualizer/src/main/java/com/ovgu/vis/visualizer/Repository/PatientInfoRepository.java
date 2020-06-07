package com.ovgu.vis.visualizer.Repository;

import com.ovgu.vis.visualizer.Entity.PatientInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.util.List;

public interface PatientInfoRepository extends JpaRepository <PatientInfo, Integer> {

    @Query("select snapshot from PatientInfo")
    public List<String> getSnapshots();

    @Query("select threeDimensionalImage from PatientInfo")
    public List<String> getThreeDImage();



}
