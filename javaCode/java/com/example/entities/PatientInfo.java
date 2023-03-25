package com.example.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import com.example.customannotations.EmailValidation;

@Entity
@Table(name="PATIENT_INFO")
public class PatientInfo  implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private int patientId =-1;
    private String patientName = null;
    @Column(name = "DATE_OF_REGISTRATION")
    private String dateOfRegitstration = null;
    
    
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
