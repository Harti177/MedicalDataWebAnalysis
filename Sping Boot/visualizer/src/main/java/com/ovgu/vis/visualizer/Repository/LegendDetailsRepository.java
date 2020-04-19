package com.ovgu.vis.visualizer.Repository;

import com.ovgu.vis.visualizer.DTO.LegendCodeValuePair;
import com.ovgu.vis.visualizer.Entity.LegendDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface LegendDetailsRepository extends JpaRepository<LegendDetails,Integer> {

    @Query("Select DISTINCT (l.key) from LegendDetails l")
    public List<String> findUniqueKeys();

    @Query("Select new com.ovgu.vis.visualizer.DTO.LegendCodeValuePair(l.value,l.legend) from LegendDetails l where l.key = :key")
    public List<LegendCodeValuePair> findLegendsByKey(String key);

    @Query("Select l.legend from LegendDetails l where l.key = :key and l.value = :value")
    public String getJoinRecords(String key, String value);

}
