package com.ovgu.vis.visualizer.Service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.ovgu.vis.visualizer.Entity.LegendDetails;
import com.ovgu.vis.visualizer.Repository.LegendDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReadLegendFromCSV {
    @Autowired
    LegendDetailsServiceImpl legendDetailsService;
    @Autowired
    LegendDetailsRepository legendDetailsRepository;


    public void readLegendCSV(String folder) throws IOException, CsvException
    {

        List<String[]> allData = new ArrayList<String[]>();

        List legendFromDB = legendDetailsRepository.findAll();
        Stream<Path> stream = Files.find(Paths.get(folder), 1, (path, basicFileAttributes) -> {
            return path.getFileName().toString().equals("legend_details.csv");
        });


        FileReader filereader = new FileReader(stream.collect(Collectors.toList()).get(0).toFile());

        //CSVReader reader = new CSVReader(filereader);
        CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
        allData = csvReader.readAll();
        if(legendFromDB.size() == 0) {
            for (String[] row : allData) {
                legendDetailsService.createLegends(new LegendDetails(row[0].trim().replace(" ","").toLowerCase(), row[1].trim(), row[2].trim()));

            }
        }

        else{
            allData.forEach(data -> {
                AtomicBoolean available = new AtomicBoolean(false);
                legendFromDB.forEach(legend -> {
                    if(((LegendDetails)legend).getValue().equals(data[1]))
                        available.set(true);
                });
                if(available.get() == false)
                    legendDetailsService.createLegends(new LegendDetails(data[0].trim().replace(" ","").toLowerCase(), data[1].trim(), data[2].trim()));
            });
//
            }
        }


    public void ReadLegendFromCSV(String directoryPath){
        try {
            readLegendCSV(directoryPath);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}



