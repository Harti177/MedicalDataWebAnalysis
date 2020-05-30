package com.ovgu.vis.visualizer.Repository;
import com.ovgu.vis.visualizer.Entity.PatientDetails;
import com.ovgu.vis.visualizer.Entity.PatientInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface PatientDetailsRepository extends JpaRepository <PatientDetails, Integer> {
}
