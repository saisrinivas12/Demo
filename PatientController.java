package com.example.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.entities.PatientInfo;
import com.example.jpa.PatientRepository;

import ch.qos.logback.classic.Logger;

@RestController
@ComponentScan(basePackages = {"com.example.entities","com.example.jpa"})
@EntityScan(basePackages = "com.example.entities")
public class PatientController {
	@Autowired
	private PatientRepository repo;
	
	@GetMapping("/")
	public ModelAndView getPage(Model model) {
		ModelAndView mv = new ModelAndView("registration");
		model.addAttribute("Patient", new PatientInfo());
		return mv;
	}
	@PostMapping("/RegisterPatient")
	
	public PatientInfo saveInfo(@ModelAttribute("Patient") PatientInfo patient) {
		System.out.println("Hello");
		return repo.save(patient);
		
	}
	@GetMapping("updatePatientInfo")
	public ModelAndView updatePatient() {
		ModelAndView mv = new ModelAndView("updatePatient");
		mv.addObject("updatePatient",new PatientInfo());
		return mv;
	}
	@PostMapping("updatePatient")
	public PatientInfo updatePatientInfo(@ModelAttribute("updatePatient")PatientInfo patient) {
		
		return repo.save(patient);
	}
	@GetMapping("/deletePatient")
	public ModelAndView deletePatient() {
		ModelAndView mv = new ModelAndView("deletePatient");
		mv.addObject("deletePatient",new PatientInfo());
		return mv;
	}
	@PostMapping("/deletePatientInfo")
	public void deletePatient(@ModelAttribute("deletePatient")PatientInfo patient,Model m){
		try {
			System.out.println("delete "+patient);
		repo.deleteById(patient.getPatientId());
		m.addAttribute("result","deleted Successfully");
		}
		catch(Exception e)
		{
		  System.out.println(e);
		}
	}
	@GetMapping("/getPatients")
	public ModelAndView getPatients() {
		ModelAndView mv = new ModelAndView("patients");
		List<PatientInfo> patients = repo.findAll();
		System.out.print(patients);
		mv.addObject("listPatients", patients);
		return mv;
	}
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("login", new PatientInfo());
		return mv;
	}
	@PostMapping("/loginCheck")
	public ModelAndView loginCheck(@ModelAttribute("login")PatientInfo patient) {
		boolean flag =false;
		System.out.print("in login"+patient);
		List<PatientInfo>patients = repo.findAll();
		ModelAndView mv;
		for(PatientInfo p :patients) {
			if(p.getPatientName().equals(patient.getPatientName())) {
				flag=true;
				break;
			}
		}
		if(flag)
		{
			mv = new ModelAndView("registration");
			mv.addObject("result","you are currently logged in");
			return mv;
		}
		System.out.print("ok in login");
		mv = new ModelAndView("error");
		return mv;
		
		
	}
	
	

}
