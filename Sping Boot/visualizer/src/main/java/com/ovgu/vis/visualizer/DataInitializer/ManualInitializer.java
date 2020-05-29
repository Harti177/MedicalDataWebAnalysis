//package com.ovgu.vis.visualizer.DataInitializer;
//
//import com.ovgu.vis.visualizer.Entity.LegendDetails;
//import com.ovgu.vis.visualizer.Entity.PatientDetails;
//import com.ovgu.vis.visualizer.Entity.PatientInfo;
//import com.ovgu.vis.visualizer.ServiceInterface.LegendDetailsService;
//import com.ovgu.vis.visualizer.ServiceInterface.PatientInfoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class ManualInitializer implements CommandLineRunner {
//
//    @Autowired
//    @Qualifier("patientInfo")
//    private PatientInfoService patientInfoService;
//
//    @Autowired
//    @Qualifier("legendDetails")
//    private LegendDetailsService legendDetailsService;
//
//    @Override
//    public void run(String... args) throws Exception {
//        List<PatientDetails> patientDetails = new ArrayList<>();
//        patientDetails.add(new PatientDetails("BA_20160321","Aneurysm","aneurysmType","N/A"));
//        patientDetails.add(new PatientDetails("BA_20160321","Aneurysm","aneurysmLocation","N/A"));
//        patientDetails.add(new PatientDetails("BA_20160321","Ruptured","ruptureStatus","N/A"));
//        patientDetails.add(new PatientDetails("BA_20160321","Aneurysm","multipleAneurysms","N/A"));
//        patientDetails.add(new PatientDetails("BA_20160321","","MedicalHistory","N/A"));
//        patientDetails.add(new PatientDetails("BA_20160321","","Notes","N/A"));
//        patientInfoService.createPatientDetails(new PatientInfo("BA_20160321","UniversityHospitalMagdeburg","N/A","N/A","3DDSA","13-04-2020","","",patientDetails));
//        patientDetails.clear();
//
//        patientDetails.add(new PatientDetails("BA_20170726","Aneurysm","aneurysmType","N/A"));
//        patientDetails.add(new PatientDetails("BA_20170726","Aneurysm","aneurysmLocation","N/A"));
//        patientDetails.add(new PatientDetails("BA_20170726","Ruptured","ruptureStatus","N/A"));
//        patientDetails.add(new PatientDetails("BA_20170726","Aneurysm","multipleAneurysms","N/A"));
//        patientDetails.add(new PatientDetails("BA_20170726","","MedicalHistory","N/A"));
//        patientDetails.add(new PatientDetails("BA_20170726","","Notes","N/A"));
//        patientInfoService.createPatientDetails(new PatientInfo("BA_20170726","UniversityHospitalMagdeburg","N/A","N/A","3DDSA","10-04-2020","","",patientDetails));
//        patientDetails.clear();
//        patientDetails.add(new PatientDetails("BC_20150912","Aneurysm","aneurysmType","N/A"));
//        patientDetails.add(new PatientDetails("BC_20150912","Aneurysm","aneurysmLocation","4"));
//        patientDetails.add(new PatientDetails("BC_20150912","Ruptured","ruptureStatus","R"));
//        patientDetails.add(new PatientDetails("BC_20150912","Aneurysm","multipleAneurysms","False"));
//        patientDetails.add(new PatientDetails("BC_20150912","","MedicalHistory","N/A"));
//        patientDetails.add(new PatientDetails("BC_20150912","","Notes","N/A"));
//        patientInfoService.createPatientDetails(new PatientInfo("BC_20150912","UniversityHospitalMagdeburg","M","36","3DDSA","13-03-2020","","",patientDetails));
//        patientDetails.clear();
//        patientDetails.add(new PatientDetails("BC_20160202","Aneurysm","aneurysmType","N/A"));
//        patientDetails.add(new PatientDetails("BC_20160202","Aneurysm","aneurysmLocation","4"));
//        patientDetails.add(new PatientDetails("BC_20160202","Ruptured","ruptureStatus","U"));
//        patientDetails.add(new PatientDetails("BC_20160202","Aneurysm","multipleAneurysms","False"));
//        patientDetails.add(new PatientDetails("BC_20160202","","MedicalHistory","N/A"));
//        patientDetails.add(new PatientDetails("BC_20160202","","Notes","N/A"));
//        patientInfoService.createPatientDetails(new PatientInfo("BC_20160202","UniversityHospitalMagdeburg","F","68","N/A","10-03-2020","","",patientDetails));
//        patientDetails.clear();
//        patientDetails.add(new PatientDetails("BL_20150731","Aneurysm","aneurysmType","N/A"));
//        patientDetails.add(new PatientDetails("BL_20150731","Aneurysm","aneurysmLocation","4"));
//        patientDetails.add(new PatientDetails("BL_20150731","Ruptured","ruptureStatus","R"));
//        patientDetails.add(new PatientDetails("BL_20150731","Aneurysm","multipleAneurysms","False"));
//        patientDetails.add(new PatientDetails("BL_20150731","","MedicalHistory","N/A"));
//        patientDetails.add(new PatientDetails("BL_20150731","","Notes","N/A"));
//        patientInfoService.createPatientDetails(new PatientInfo("BL_20150731","UniversityHospitalMagdeburg","M","86","3DDSA","13-02-2020","","",patientDetails));
//        patientDetails.clear();
//        patientDetails.add(new PatientDetails("BM_20170828","Aneurysm","aneurysmType","N/A"));
//        patientDetails.add(new PatientDetails("BM_20170828","Aneurysm","aneurysmLocation","6"));
//        patientDetails.add(new PatientDetails("BM_20170828","Ruptured","ruptureStatus","R"));
//        patientDetails.add(new PatientDetails("BM_20170828","Aneurysm","multipleAneurysms","True"));
//        patientDetails.add(new PatientDetails("BM_20170828","","MedicalHistory","N/A"));
//        patientDetails.add(new PatientDetails("BM_20170828","","Notes","N/A"));
//        patientInfoService.createPatientDetails(new PatientInfo("BM_20170828","UniversityHospitalMagdeburg","F","51","3DDSA","10-02-2020","","",patientDetails));
//        patientDetails.clear();
//        patientDetails.add(new PatientDetails("BM_20170828","Aneurysm","aneurysmType","N/A"));
//        patientDetails.add(new PatientDetails("BM_20170828","Aneurysm","aneurysmLocation","4"));
//        patientDetails.add(new PatientDetails("BM_20170828","Ruptured","ruptureStatus","U"));
//        patientDetails.add(new PatientDetails("BM_20170828","Aneurysm","multipleAneurysms","Ture"));
//        patientDetails.add(new PatientDetails("BM_20170828","","MedicalHistory","N/A"));
//        patientDetails.add(new PatientDetails("BM_20170828","","Notes","N/A"));
//        patientInfoService.createPatientDetails(new PatientInfo("BM_20170828","UniversityHospitalMagdeburg","F","51","3DDSA","13-01-2020","","",patientDetails));
//        patientDetails.clear();
//        patientDetails.add(new PatientDetails("BN_20150317","Aneurysm","aneurysmType","N/A"));
//        patientDetails.add(new PatientDetails("BN_20150317","Aneurysm","aneurysmLocation","4"));
//        patientDetails.add(new PatientDetails("BN_20150317","Ruptured","ruptureStatus","R"));
//        patientDetails.add(new PatientDetails("BN_20150317","Aneurysm","multipleAneurysms","False"));
//        patientDetails.add(new PatientDetails("BN_20150317","","MedicalHistory","N/A"));
//        patientDetails.add(new PatientDetails("BN_20150317","","Notes","N/A"));
//        patientInfoService.createPatientDetails(new PatientInfo("BN_20150317","UniversityHospitalMagdeburg","M","53","N/A","10-01-2020","","",patientDetails));
//        patientDetails.clear();
//        patientDetails.add(new PatientDetails("BP_20161021","Aneurysm","aneurysmType","N/A"));
//        patientDetails.add(new PatientDetails("BP_20161021","Aneurysm","aneurysmLocation","2"));
//        patientDetails.add(new PatientDetails("BP_20161021","Ruptured","ruptureStatus","R"));
//        patientDetails.add(new PatientDetails("BP_20161021","Aneurysm","multipleAneurysms","True"));
//        patientDetails.add(new PatientDetails("BP_20161021","","MedicalHistory","N/A"));
//        patientDetails.add(new PatientDetails("BP_20161021","","Notes","N/A"));
//        patientInfoService.createPatientDetails(new PatientInfo("BP_20161021","UniversityHospitalMagdeburg","F","55","N/A","13-11-2019","","",patientDetails));
//        patientDetails.clear();
//        patientDetails.add(new PatientDetails("BP_20161021","Aneurysm","aneurysmType","N/A"));
//        patientDetails.add(new PatientDetails("BP_20161021","Aneurysm","aneurysmLocation","2"));
//        patientDetails.add(new PatientDetails("BP_20161021","Ruptured","ruptureStatus","U"));
//        patientDetails.add(new PatientDetails("BP_20161021","Aneurysm","multipleAneurysms","Ture"));
//        patientDetails.add(new PatientDetails("BP_20161021","","MedicalHistory","N/A"));
//        patientDetails.add(new PatientDetails("BP_20161021","","Notes","N/A"));
//        patientInfoService.createPatientDetails(new PatientInfo("BP_20161021","UniversityHospitalMagdeburg","F","55","N/A","10-11-2019","","",patientDetails));
//        patientDetails.clear();
//        patientDetails.add(new PatientDetails("BU_20150801","Aneurysm","aneurysmType","N/A"));
//        patientDetails.add(new PatientDetails("BU_20150801","Aneurysm","aneurysmLocation","4"));
//        patientDetails.add(new PatientDetails("BU_20150801","Ruptured","ruptureStatus","R"));
//        patientDetails.add(new PatientDetails("BU_20150801","Aneurysm","multipleAneurysms","False"));
//        patientDetails.add(new PatientDetails("BU_20150801","","MedicalHistory","N/A"));
//        patientDetails.add(new PatientDetails("BU_20150801","","Notes","N/A"));
//        patientInfoService.createPatientDetails(new PatientInfo("BU_20150801","UniversityHospitalMagdeburg","F","93","3DDSA","10-11-2019","","",patientDetails));
//        //Legend Data
//        legendDetailsService.createLegends(new LegendDetails("ruptureStatus","R","Ruptured"));
//        legendDetailsService.createLegends(new LegendDetails("ruptureStatus","U","Unruptured"));
//        legendDetailsService.createLegends(new LegendDetails("aneurysmLocation","1","M1"));
//        legendDetailsService.createLegends(new LegendDetails("aneurysmLocation","2","MCA-Bif"));
//        legendDetailsService.createLegends(new LegendDetails("aneurysmLocation","3","M2"));
//        legendDetailsService.createLegends(new LegendDetails("aneurysmLocation","4","Acom"));
//        legendDetailsService.createLegends(new LegendDetails("aneurysmLocation","5","Pericall"));
//        legendDetailsService.createLegends(new LegendDetails("aneurysmLocation","6","Pcom"));
//        legendDetailsService.createLegends(new LegendDetails("aneurysmLocation","7","basilar trip"));
//        legendDetailsService.createLegends(new LegendDetails("aneurysmLocation","8","PICA"));
//        legendDetailsService.createLegends(new LegendDetails("aneurysmLocation","9","ICA"));
//        legendDetailsService.createLegends(new LegendDetails("aneurysmLocation","10","Carotid"));
//        legendDetailsService.createLegends(new LegendDetails("aneurysmLocation","11","PCA"));
//        legendDetailsService.createLegends(new LegendDetails("aneurysmLocation","12","AchoA"));
//        legendDetailsService.createLegends(new LegendDetails("aneurysmLocation","13","A1"));
//        legendDetailsService.createLegends(new LegendDetails("aneurysmLocation","14","paraophth"));
//        legendDetailsService.createLegends(new LegendDetails("aneurysmLocation","15","infraophth"));
//        legendDetailsService.createLegends(new LegendDetails("aneurysmLocation","16","callosomarginalis"));
//        legendDetailsService.createLegends(new LegendDetails("aneurysmType","LAT","Lateral/Side Wall"));
//        legendDetailsService.createLegends(new LegendDetails("aneurysmType","TER","Terminal/ BIfurcation aneurysm"));
//    }
//}
