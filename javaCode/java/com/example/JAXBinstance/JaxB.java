package com.example.JAXBinstance;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;

import javax.security.auth.Destroyable;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.example.Controllers.PatientController;
import com.example.entities.Employee;
import com.example.entities.PatientInfo;


@Configuration
@Service
public class JaxB {
	
	private Properties properties;

	@Bean
	public JAXBContext jaxb() {
		try {
		System.out.println(" In jax B");
			
			return JAXBContext.newInstance(Employee.class);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  null;
	}
	@Bean
	public File empFile() {
		return new File("Employee.xml");
	}
	
	@Bean("patientInfo")
	public PatientInfo getPatientInfo() {
		
		return new PatientInfo();
	}
	
	/*@Bean("mobileProperties")
	public Optional<Properties> getMobileProperties() {
		InputStream in = null;
		Properties p = null;
		try {
			 in = new FileInputStream(new File(""));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("exception occurred "+e);
			e.printStackTrace();
		}
		if(in!=null) {
			
			try {
				p = new Properties();
				p.load(in);
			} catch (IOException e) {
				 System.out.println("Exception has been there");
			}
			
			
			
		}
		
		return Optional.of(p);
		
 	}*/
	
}
