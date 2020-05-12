package com.ovgu.vis.visualizer.DAO.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Filter {

    private String fieldName;

    private String type;

    private String start;

    private String end;

    private List<String> values ;

    public String getFieldName() {
        return fieldName;
    }

    public String getType() {
        return type;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public List<String> getValues() {
        return values;
    }
}
