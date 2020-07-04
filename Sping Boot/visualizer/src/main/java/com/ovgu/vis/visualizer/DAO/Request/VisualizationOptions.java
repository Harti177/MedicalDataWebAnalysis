package com.ovgu.vis.visualizer.DAO.Request;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VisualizationOptions {

    private String xAxis;

    private String yAxis;

    private String target;

    private  String targetAdditional;

    private String plotType;

    public String getXAxis() {
        return xAxis;
    }

    public String getYAxis() {
        return yAxis;
    }

    public String getTarget() {
        return target;
    }

    public String getPlotType() {
        return plotType;
    }

    public String getTargetAdditional() {
        return targetAdditional;
    }
}
