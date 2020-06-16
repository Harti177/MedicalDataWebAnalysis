package com.ovgu.vis.visualizer.ServiceInterface;

import com.ovgu.vis.visualizer.DAO.Request.FilterRequestBody;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface PatientRecordService {

    ResponseEntity getPatients(FilterRequestBody filterConditions);

    byte[] getImage(String imagePath) throws IOException;

}
