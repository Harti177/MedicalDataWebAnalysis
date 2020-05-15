package com.ovgu.vis.visualizer.Service;

import com.ovgu.vis.visualizer.DTO.LegendList;
import com.ovgu.vis.visualizer.ServiceInterface.LegendDetailsService;
import com.ovgu.vis.visualizer.Entity.LegendDetails;
import com.ovgu.vis.visualizer.Repository.LegendDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("legendDetails")
class LegendDetailsServiceImpl implements LegendDetailsService {

    @Autowired
    LegendDetailsRepository legendDetailsRepository;

    @Autowired
    private EntityManager entityManager;


    @Override
    public void createLegends(LegendDetails legendDetails){
        legendDetailsRepository.save(legendDetails);
    }

    @Override
    public List<LegendList> getAllLegends(){
        List<LegendList> legendList = new ArrayList<>();
        List<String> uniqueKeys = legendDetailsRepository.findUniqueKeys();
        uniqueKeys.forEach(key -> legendList.add(new LegendList(key,legendDetailsRepository.findLegendsByKey(key))));
        return legendList;
    }

    @Override
    public void updateLegend(String key, String value, String legend){
        legendDetailsRepository.updateLegendWithKeyValue(key,value,legend);
    }

    @Override
    public void deleteLegend(String key, String value){
        legendDetailsRepository.deleteLegend(key,value);
    }
}
