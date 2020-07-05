package com.ovgu.vis.visualizer.DAO.Response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Visualization {

    private List labels = new ArrayList();

    private List data = new ArrayList();

    private List xAxis = new ArrayList();

    private List yAxis = new ArrayList();

    private List target = new ArrayList();

    private List targetAdditional = new ArrayList();

    public Visualization(){}

    public void setVisualizationData(List labels, List data) {
        this.labels.add(labels);
        this.data.add(data);
    }

    public void setxAxis(List xAxis) {
        this.xAxis = xAxis;
    }

    public void setyAxis(List yAxis) {
        this.yAxis = yAxis;
    }

    public void setTarget(List target) {
        this.target = target;
    }

    public void setTargetAdditional(List targetAdditional) {
        this.targetAdditional = targetAdditional;
    }
}
