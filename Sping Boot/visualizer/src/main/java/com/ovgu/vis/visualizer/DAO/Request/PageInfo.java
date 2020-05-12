package com.ovgu.vis.visualizer.DAO.Request;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PageInfo {

    private int pageNumber;

    private int numberOfRecordsPerPage;

    public int getPageNumber() {
        return pageNumber;
    }

    public int getNumberOfRecordsPerPage() {
        return numberOfRecordsPerPage;
    }
}
