package com.ovgu.vis.visualizer.ServiceInterface;

import com.ovgu.vis.visualizer.DTO.LegendList;
import com.ovgu.vis.visualizer.Entity.LegendDetails;

import java.util.List;


public interface LegendDetailsService {

    void createLegends(LegendDetails legendDetails);

    List<LegendList> getAllLegends();

    void updateLegend(String key, String value, String legend);

    void deleteLegend(String key, String value);

}
