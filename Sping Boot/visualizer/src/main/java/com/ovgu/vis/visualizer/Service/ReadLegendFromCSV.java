package com.ovgu.vis.visualizer.Service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import com.ovgu.vis.visualizer.Entity.LegendDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ReadLegendFromCSV {
    @Autowired
    LegendDetailsServiceImpl legendDetailsService;


    public void readLegendCSV(String folder) throws IOException, CsvException
    {
        //Path testPath = Paths.get(folder);
        Stream<Path> stream = Files.find(Paths.get(folder), 1,(path, basicFileAttributes) -> {
            return path.getFileName().toString().equals("legend_details.csv");
        });
        //List a = stream.collect(Collectors.toList());

        FileReader filereader =  new FileReader(stream.collect(Collectors.toList()).get(0).toFile());
        // create csvReader object and skip first Line
        CSVReader reader = new CSVReader(filereader);
        CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
        List<String[]> allData = csvReader.readAll();
        for( String[] row : allData)
        {
            legendDetailsService.createLegends(new LegendDetails(row[0],row[1],row[2]));

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



