package com.ovgu.vis.visualizer.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class FilterRequestBody {

    private String field;

    private List<String> filterData  = new ArrayList<>();

    public FilterRequestBody(String field, List<String> filterData) {
        this.field = field;
        this.filterData = filterData;
    }
}
