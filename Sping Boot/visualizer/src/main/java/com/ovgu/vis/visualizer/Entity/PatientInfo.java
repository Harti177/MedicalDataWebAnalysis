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

	@Id @GeneratedValue
	private long id;

	@NonNull
	private String patientId;
	
	private String institute;
	
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
		this.id = id;
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

}
