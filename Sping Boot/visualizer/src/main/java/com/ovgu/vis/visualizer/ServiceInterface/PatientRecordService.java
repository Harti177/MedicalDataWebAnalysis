package com.ovgu.vis.visualizer.ServiceInterface;

import com.ovgu.vis.visualizer.DAO.Request.FilterRequestBody;
import org.springframework.http.ResponseEntity;

public interface PatientRecordService {

    ResponseEntity getPatients(FilterRequestBody filterConditions);

}
