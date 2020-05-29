package com.ovgu.vis.visualizer.DAO.Response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Response {

    private long totalCasesFound;

    private int currentPage;

    private int totalPage;

    private int offset;

    List<Patient> data;

    public Response(long found, int currentPage, int totalPage, int offset, List<Patient> patients) {
        this.totalCasesFound = found;
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.offset = offset;
        this.data = patients;
    }
}
