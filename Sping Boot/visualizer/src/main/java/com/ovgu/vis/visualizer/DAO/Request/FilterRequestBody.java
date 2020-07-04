package com.ovgu.vis.visualizer.DAO.Request;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FilterRequestBody {


    private List<Filter> filters;

    private DisplayOrder displayOrder;

    private PageInfo pageInfo;

    private boolean toVisualize;

    private VisualizationOptions visualizationOptions;

    /*
    * Getters for all attributes
    * */
    public List<Filter> getFilters() {
        return filters;
    }

    public DisplayOrder getDisplayOrder() {
        return displayOrder;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public boolean isVisualize() {
        return toVisualize;
    }

    public VisualizationOptions getVisualizationOptions() {
        return visualizationOptions;
    }
}
