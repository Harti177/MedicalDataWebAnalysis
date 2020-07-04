package com.ovgu.vis.visualizer.Repository;
import com.ovgu.vis.visualizer.Entity.PatientDetails;
import com.ovgu.vis.visualizer.Entity.PatientInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PatientDetailsRepository extends JpaRepository <PatientDetails, Integer> {

    @Query("select distinct (value) from PatientDetails where key = :key")
    public List<String> getUniqueValue(String key);

}
