package com.ovgu.vis.visualizer.Entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ovgu.vis.visualizer.Entity.AttributeConverter.AgeConverter;
import com.ovgu.vis.visualizer.Entity.AttributeConverter.DateConverter;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@ToString
@Entity
public class PatientInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@NonNull
	private String patientId;
	
	private String institute;

	private String sex;
	
	@Convert(converter = AgeConverter.class)
	private String age;
	
	private String modality;

	@Convert(converter = DateConverter.class)
	@JsonFormat(pattern = "dd-mm-yyyy")
	private String dateOfCreation;

	private String threeDimensionalImage;

	private String snapshot;

	@OneToMany(targetEntity = PatientDetails.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "patientInfoId", referencedColumnName = "id")
	private List<PatientDetails> patientDetails;

	public PatientInfo(){

	}

	public PatientInfo(String patientId, String institute, String sex, String age, String modality, String dateOfCreation, String threeDimensionalImage, String snapshot, List<PatientDetails> patientDetails) {
		this.patientId = patientId;
		this.institute = institute;
		this.sex = sex;
		AgeConverter ageConverter = new AgeConverter();
		this.age = age;//ageConverter.convertToEntityAttribute(age);
		this.modality = modality;
		DateConverter dateConverter = new DateConverter();
		this.dateOfCreation = dateOfCreation;//dateConverter.convertToEntityAttribute(createdDate);
		this.threeDimensionalImage = threeDimensionalImage;
		this.snapshot = snapshot;
		this.patientDetails = patientDetails;
	}

	public List<PatientDetails> getPatientDetails() {
		return patientDetails;
	}

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
		return this.age;
	}

	public String getModality() {
		return modality;
	}

	public String getDateOfCreation() {
		return this.dateOfCreation;
	}

	public String getThreeDimensionalImage() {
		return threeDimensionalImage;
	}

	public String getSnapshot() {
		return snapshot;
	}
}
