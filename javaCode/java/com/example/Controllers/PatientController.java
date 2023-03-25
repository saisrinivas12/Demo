package com.example.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.result.view.RedirectView;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.pattern.PathPattern.PathMatchInfo;

import com.example.entities.Laptop;
import com.example.entities.PatientInfo;
import com.example.entities.User;
import com.example.jpa.PatientRepository;
import com.example.jpa.UserRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.JsonObject;

import ch.qos.logback.classic.Logger;

@RestController
@ComponentScan(basePackages = {"com.example.entities","com.example.jpa"})
@EntityScan(basePackages = "com.example.entities")
public class PatientController {
	@Autowired
	private PatientRepository repo;
	@Autowired 
	private PatientInfo patientInfo;
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/")
	public ModelAndView getPage(Model model) {
		ModelAndView mv = new ModelAndView("registration");
		model.addAttribute("Patient", new PatientInfo());
		return mv;
	}
	@PostMapping("/RegisterPatient")
	
	public PatientInfo saveInfo(@ModelAttribute("Patient")PatientInfo patient) {
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
	public Optional<List<PatientInfo>> getPatients() {
		//ModelAndView mv = new ModelAndView("patients");
		List<PatientInfo> patients = repo.findAll();
		return Optional.ofNullable(patients);
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
		
			mv = new ModelAndView("registration");
			if(flag) {
			mv.addObject("result","you are currently logged in");
			}
			else {
				mv.addObject("result","error occurred while login");
			}
			return mv;
		
      
		
	}
	@GetMapping("/getPatient/{name}")
	
	public PatientInfo getPatientDetails(@PathVariable("name") String patientName) {
		
		return repo.getPatientDetails(patientName);
	}
	
	@PostMapping("/savePatientEmployee")

	// sending directly the object url-formencoded
	public  PatientInfo SavePatientEmployeeData(@RequestBody PatientInfo patientInforeq) {
		System.out.println("Request received "+patientInforeq);
		return repo.save(patientInforeq);
		
		
		
 	}
	
	@PostMapping("/users")
	
	public ResponseEntity<User>updateUser(@RequestBody User user){
		
		List<Laptop> al = new ArrayList<>();
		
	   Laptop l1 = new Laptop();
	   l1.setLaptopId(1);
	   l1.setLaptopName("Dell");
	   
	   Laptop l2 = new Laptop();
	   l2.setLaptopId(2);
	   l2.setLaptopName("lenovo");
	
		al.add(l1);
		al.add(l2);
		
		
		user.setLaptops(al);
		
	    return new ResponseEntity<User>(userRepo.save(user),HttpStatus.OK);
	}
	@GetMapping("/allusers")
	public ResponseEntity<List<User>> getAllUsers(){
		
		return new ResponseEntity<List<User>>(userRepo.findAll(),HttpStatus.OK);
	}

	
	

}
