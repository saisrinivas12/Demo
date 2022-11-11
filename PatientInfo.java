package com.example.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PATIENT_INFO")
public class PatientInfo {
    @Id
	private int patientId;
    private String patientName;
    @Column(name = "DATE_OF_REGISTRATION")
    private String dateOfRegitstration;
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getDateOfRegitstration() {
		return dateOfRegitstration;
	}
	@Override
	public String toString() {
		return "PatientInfo [patientId=" + patientId + ", patientName=" + patientName + ", dateOfRegitstration="
				+ dateOfRegitstration + "]";
	}
	public void setDateOfRegitstration(String dateOfRegitstration) {
		this.dateOfRegitstration = dateOfRegitstration;
	}
    
	
}
