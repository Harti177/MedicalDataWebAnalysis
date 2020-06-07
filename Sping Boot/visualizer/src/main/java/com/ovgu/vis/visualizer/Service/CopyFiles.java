package com.ovgu.vis.visualizer.Service;


import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.ovgu.vis.visualizer.Repository.PatientInfoRepository;

@Service
public class CopyFiles {

    @Autowired
    PatientInfoRepository patientInfoRepository;

    public void copyFiles(List<String> fileList, Path path) throws IOException {

        for(String file : fileList) {
            if(file != "")
            {
                FileUtils.copyFileToDirectory(Paths.get(file).toFile(), path.toFile());
            }

        }

    }
    public void copy3DFiles(String directoryPath,Boolean snapshot,  Boolean threeDImage ) throws IOException {
        if(snapshot==Boolean.TRUE && threeDImage == Boolean.FALSE)
        {
            List<String> fileList= patientInfoRepository.getSnapshots();
            copyFiles(fileList,Paths.get(directoryPath+"//snapshot"));
        }
        else if (threeDImage == Boolean.TRUE && snapshot==Boolean.FALSE)
        {
            List<String> fileList = patientInfoRepository.getThreeDImage();
            copyFiles(fileList, Paths.get(directoryPath + "//threeDimage"));
        }
        else if (threeDImage == Boolean.TRUE && snapshot == Boolean.TRUE)
        {
            List<String> fileList = patientInfoRepository.getSnapshots();
            copyFiles(fileList, Paths.get(directoryPath + "//snapshot"));
            List<String> fileList1 = patientInfoRepository.getThreeDImage();
            copyFiles(fileList1, Paths.get(directoryPath + "//threeDimage"));
        }

    }
}
