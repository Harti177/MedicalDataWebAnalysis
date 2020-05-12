package com.ovgu.vis.visualizer.Repository;

import com.ovgu.vis.visualizer.DTO.LegendCodeValuePair;
import com.ovgu.vis.visualizer.Entity.LegendDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface LegendDetailsRepository extends JpaRepository<LegendDetails,Integer> {

    @Query("Select DISTINCT (key) from LegendDetails")
    public List<String> findUniqueKeys();

    @Query("Select new com.ovgu.vis.visualizer.DTO.LegendCodeValuePair(value,legend) from LegendDetails where key = :key")
    public List<LegendCodeValuePair> findLegendsByKey(String key);

    @Query("Select legend from LegendDetails where key = :key and value = :value")
    public String getJoinRecords(String key, String value);

    @Transactional
    @Modifying
    @Query ("update LegendDetails set legend = :legend where key = :key and value = :value")
    public void updateLegendWithKeyValue(String key, String value, String legend);

    @Transactional
    @Modifying
    @Query ("delete from LegendDetails where key = :key and value = :value")
    public void deleteLegend(String key, String value);
}
