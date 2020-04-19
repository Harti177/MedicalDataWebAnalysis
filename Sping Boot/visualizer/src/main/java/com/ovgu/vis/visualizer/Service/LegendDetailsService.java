package com.ovgu.vis.visualizer.Service;

import com.ovgu.vis.visualizer.DTO.LegendList;
import com.ovgu.vis.visualizer.Entity.LegendDetails;
import com.ovgu.vis.visualizer.Repository.LegendDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public class LegendDetailsService {

    @Autowired
    LegendDetailsRepository legendDetailsRepository;

    public void createLegends(LegendDetails legendDetails){
        legendDetailsRepository.save(legendDetails);
    }

    public List<LegendList> getAllLegends(){
        List<LegendList> legendList = new ArrayList<>();
        List<String> uniqueKeys = legendDetailsRepository.findUniqueKeys();
        uniqueKeys.forEach(key -> legendList.add(new LegendList(key,legendDetailsRepository.findLegendsByKey(key))));
        return legendList;
    }
}
