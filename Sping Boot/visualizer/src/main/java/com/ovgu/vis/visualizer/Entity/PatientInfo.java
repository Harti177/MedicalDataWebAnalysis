package com.ovgu.vis.visualizer.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ovgu.vis.visualizer.AttributeConverter.AgeConverter;
import com.ovgu.vis.visualizer.AttributeConverter.DateConverter;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
@Entity
public class PatientInfo {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NonNull
	private String patientId;
	
	private String institute;

	public String getPatientId() {
		return patientId;
	}

	public String getInstitute() {
		return institute;
	}

	public String getSex() {
		return sex;
	}

	public String getAge() {
		AgeConverter ageConverter = new AgeConverter();
		return ageConverter.convertToDatabaseColumn(age);
	}

	public String getModality() {
		return modality;
	}

	public String getCreatedDate() {
		DateConverter dateConverter = new DateConverter();
		return dateConverter.convertToDatabaseColumn(createdDate);
	}

	public String getThreeDimensionalImage() {
		return threeDimensionalImage;
	}

	public String getSnapshot() {
		return snapshot;
	}

	private String sex;
	
	@Convert(converter = AgeConverter.class)
	private int age;
	
	private String modality;

	@Convert(converter = DateConverter.class)
	@JsonFormat(pattern = "dd/mm/yyyy")
	private Date createdDate;

	private String threeDimensionalImage;

	private String snapshot;

	@OneToMany(targetEntity = PatientDetails.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "patientInfoId", referencedColumnName = "id")
	private List<PatientDetails> patientDetails;

	public PatientInfo(){

	}

	public PatientInfo(String patientId, String institute, String sex, String age, String modality, String createdDate, String threeDimensionalImage, String snapshot, List<PatientDetails> patientDetails) {
		this.patientId = patientId;
		this.institute = institute;
		this.sex = sex;
		AgeConverter ageConverter = new AgeConverter();
		this.age = ageConverter.convertToEntityAttribute(age);
		this.modality = modality;
		DateConverter dateConverter = new DateConverter();
		this.createdDate = dateConverter.convertToEntityAttribute(createdDate);
		this.threeDimensionalImage = threeDimensionalImage;
		this.snapshot = snapshot;
		this.patientDetails = patientDetails;
	}

	public List<PatientDetails> getPatientDetails() {
		return patientDetails;
	}
}
