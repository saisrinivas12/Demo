package com.example.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.PatientInfo;
import com.example.entities.User;

@Repository

public interface PatientRepository extends JpaRepository<PatientInfo, Integer> {
	
	
	@Query("from PatientInfo where patient_name=:patientName")
	public PatientInfo getPatientDetails(@Param(value = "patientName") String patientName);
	


}
