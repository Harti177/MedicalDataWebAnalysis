package com.ovgu.vis.visualizer.DAO.Request;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DisplayOrder {

    private String fieldName;

    private String sortType;

    public String getFieldName() {
        return fieldName;
    }

    public String getSortType() {
        return sortType;
    }
}
