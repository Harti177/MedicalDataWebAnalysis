package com.ovgu.vis.visualizer.DAO.Response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Visualization {

    private List labels = new ArrayList();

    private List data = new ArrayList();

    public Visualization(){}

    public void setVisualizationData(List labels, List data) {
        this.labels.add(labels);
        this.data.add(data);
    }
}
