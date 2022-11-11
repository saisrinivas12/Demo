package com.example.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entities.PatientInfo;

public interface PatientRepository extends JpaRepository<PatientInfo, Integer> {
	
	

}
