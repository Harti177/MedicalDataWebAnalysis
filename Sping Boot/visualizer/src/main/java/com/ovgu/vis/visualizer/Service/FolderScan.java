package com.ovgu.vis.visualizer.Service;


//import com.opencsv.CSVParser;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.exceptions.CsvException;
import com.ovgu.vis.visualizer.Entity.PatientDetails;
import com.ovgu.vis.visualizer.Entity.PatientInfo;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.ovgu.vis.visualizer.Service.DeleteItemsFromDB;

@Service
public class FolderScan {

    @Autowired
    PatientInfoServiceImpl patientInfoService;

    @Autowired
    LegendDetailsServiceImpl legendDetailsService;

    @Autowired
    DeleteItemsFromDB deleteItemsFromDB;
    @Autowired
    ReadLegendFromCSV readLegendFromCSV;

    private static String threeDfile;
    private static String snapshot;

    private ArrayList<String> output = new ArrayList<>();


    public Stream<Path> findCSV(File folder, String patientId) throws IOException {
        //finding files containing '.csv' in name
        Stream<Path> stream = Files.find(folder.toPath(), 1, (path, basicFileAttributes) -> {
            File file = path.toFile();
            return !file.isDirectory() && ((file.getName().contains(patientId + ".csv")));
        });
        //stream.forEach(System.out::println);

        return stream;


    }

    public void patientIDAndPatientInfo(List<String[]> allData, String fileCreatedDate, File file) throws IOException {
        snapshot = null;
        threeDfile = null;
        String category;

        int rowNumber = 0;
        String[] headers = allData.get(0);
        for (String[] row : allData.subList(1, allData.size())) {
            List<PatientDetails> patientDetails = new ArrayList<>();
//            patientDetails.clear();
            String patientName = row[0];
            Stream<Path> threeDObjectFilePath = threeDObjectFile(file, patientName);
            Stream<Path> snapshotFilePath = snapshotFile(file, patientName);
            List snapshotFileList = snapshotFilePath.collect(Collectors.toList());
            List threeDObjectFileList = threeDObjectFilePath.collect(Collectors.toList());
            if (snapshotFileList.size() == 0) {
                output.add(patientName + ", No Snapshot");
                snapshot = "";
            } else {
                snapshotFileList.forEach(x -> {
                    if (x.toString().contains(".png")) {
                        snapshot = x.toString();
                    }
                });
            }
            if (threeDObjectFileList.size() == 0) {
                output.add(patientName + ",No 3DObjectFile");
                threeDfile = "";
            } else {
                threeDObjectFileList.forEach(x -> {
                    if ((x.toString().contains(".obj")) && (x.toString().contains(".stl"))) {
                        //System.out.println("contains 3d object");
                        threeDfile = x.toString();
                    } else if ((x.toString().contains(".obj")) || (x.toString().contains(".stl"))) {
                        threeDfile = x.toString();
                    }

                });
            }
            rowNumber = rowNumber + 1;
            for (int i = 5; i <= row.length - 1; i++) {
                if(headers[i].toLowerCase().contains("aneurysm")){category = "Aneurysm";}
                else if(headers[i].toLowerCase().contains("rupture")){category = "Rupture";}
                else{category = "";}
                //patientDetails.add(new PatientDetails(rowNumber, "category", a[i], row[i]));
                patientDetails.add(new PatientDetails(row[0], category, headers[i], row[i]));
            }
            patientInfoService.createPatientDetails(new PatientInfo(row[0], row[1], row[4], row[3], row[2], fileCreatedDate, threeDfile, snapshot, patientDetails));
        }

    }



    public void readCSV(Path path, File file) {
        try {
            List<String[]> allData = null;
            //file created date
            BasicFileAttributes fileattr = Files.readAttributes(path, BasicFileAttributes.class);
            String fileCreatedDate = fileattr.lastModifiedTime().toString();//.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            fileCreatedDate = fileCreatedDate.replace("T"," ").split("Z")[0];

            //read csv file
            FileReader filereader = new FileReader(path.toFile());
            // create csvReader object and skip first Line
            try {
                CSVParser csvParser = new CSVParserBuilder().withSeparator(',').build();
                CSVReader csvReader = new CSVReaderBuilder(filereader).withCSVParser(csvParser).build();
                allData = csvReader.readAll();
                patientIDAndPatientInfo(allData, fileCreatedDate.toString(), file);
            }
            catch (IOException eee)
            {
                try {
                    // try the other valid delimeter
                    CSVParser csvParser = new CSVParserBuilder().withSeparator(';').build();
                    CSVReader csvReader = new CSVReaderBuilder(filereader).withCSVParser(csvParser).build();
                    allData = csvReader.readAll();
                    patientIDAndPatientInfo(allData, fileCreatedDate.toString(), file);
                    // now read the records
                }
                catch (IOException | CsvException ee) {
                    output.add(file + ",check CSV file format");

                }
                //return response;
            }
            /*catch (Exception e) {
                //e.printStackTrace();
                output.add(file + ",check CSV file format");
                System.out.println(e.getMessage());
            }*/

        }
        catch (Exception e) {
            //e.printStackTrace();
        }

    }


        public Stream<Path> threeDObjectFile(File folder, String patientId) throws IOException {
        Path testPath = folder.toPath();
        //finding files containing '.stl' and '.obj' in name
        Stream<Path> stream = Files.find(testPath, 100, (path, basicFileAttributes) -> {
            File file = path.toFile();
            return !file.isDirectory() && ((file.getName().contains(patientId + ".stl")) || (file.getName().contains(patientId + ".obj")));
        });
        //stream.forEach(System.out::println);
        return stream;
    }

    public Stream<Path> snapshotFile(File folder, String patientId) throws IOException {
        Path testPath = folder.toPath();
        //finding files containing '.stl' and '.obj' in name
        Stream<Path> stream = Files.find(testPath, 100, (path, basicFileAttributes) -> {
            File file = path.toFile();
            return !file.isDirectory() && (file.getName().contains(patientId + ".png"));
        });
        //stream.forEach(System.out::println);
        return stream;

    }


    /*public File folderScan(String directoryPath) throws IOException {
        //directoryPath = "S:\\Shared with me\\DB_IntracranialAneurysm";

            File[] directories = new File(directoryPath).listFiles(File::isDirectory);

            System.out.println(Arrays.toString(directories));
            //findLegend(directoryPath);
            //readLegendCSV(directoryPath);
            //output = new ArrayList<String>();
            for (File file : directories) {
                System.out.println("Patient ID : " + file.getName());
                //find CSV file path
                Stream<Path> excelFilePaths = findExcel(file, file.getName());
                //read CSV file
                //excelFilePaths.forEach(System.out::println);
                excelFilePaths.forEach(x -> readCSV(x, file));

            }
            StringBuilder filecontent = new StringBuilder("patientID,Comment\n");
            for (String row : output ) {
                filecontent.append(row);
            }
            File outputfilepath = new File(directoryPath+"\\logfile.csv");
            FileWriter fileWriter = new FileWriter(directoryPath+"\\logfile.csv");
            fileWriter.write(filecontent.toString());
            fileWriter.flush();

            return outputfilepath;


        }*/



    public ResponseEntity folderScan(String directoryPath) {
        try {
            deleteItemsFromDB.deleteItemsFromDB();
            readLegendFromCSV.readLegendCSV(directoryPath);
            File[] directories = new File(directoryPath).listFiles(File::isDirectory);
            output.clear();
            output.add("patientID,Comment");
            //System.out.println(Arrays.toString(directories));
            //findLegend(directoryPath);
            //readLegendCSV(directoryPath);
            //output = new ArrayList<String>();
            if(directories.length != 0) {
                for (File file : directories) {
                    //System.out.println("Patient ID : " + file.getName());
                    //find CSV file path
                    Stream<Path> excelFilePaths = findCSV(file, file.getName());
                    //excelFilePaths.forEach(x->readEXCEL(file));
                    List paths = excelFilePaths.collect(Collectors.toList());
                    if (paths.size() == 0) {
                        output.add(file.getName() + ", No CSV file");
                    }
                    //read CSV file
                    //excelFilePaths.forEach(System.out::println);
                    else {
                        paths.forEach(x ->readCSV((Path) x, file));

                    }
                }
            }
            else{
                output.add(directoryPath + ", is empty");

            }
//            StringBuilder filecontent = new StringBuilder("patientID,Comment\n");
//            for (String row : output) {
//                filecontent.append(row);
//            }
//            File outputfilepath = new File(directoryPath + "\\logfile.csv");
//            FileWriter fileWriter = new FileWriter(directoryPath + "\\logfile.csv");
//            fileWriter.write(filecontent.toString());
//            fileWriter.flush();
//
//            InputStreamResource resourse = new InputStreamResource(new FileInputStream(outputfilepath));
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Disposition",String.format("attachment:filename=\"%s\"",outputfilepath.getName()));




            return ResponseEntity.status(HttpStatus.OK).body(output.size() == 1 ? null : output);

//                    org.springframework.http.ResponseEntity.ok()//.headers(headers)
//                    .contentLength(outputfilepath.length())
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                    .body(output);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getCause().getMessage());
        }
    }

}

