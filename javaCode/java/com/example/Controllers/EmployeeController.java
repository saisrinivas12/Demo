package com.example.Controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.entities.Employee;
import com.example.entities.EmployeeList;
import com.example.entities.PatientInfo;
import com.example.jpa.EmployeeRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@RestController


@ComponentScan(basePackages = {"com.example.entities","com.example.jpa","com.example.JAXBinstance"})
@EnableJpaRepositories(basePackages = "com.example.jpa")
@EntityScan(basePackages = "com.example.entities")



public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	@Autowired
	private JAXBContext jaxb;
	
	@Autowired 
	private File empfile;
	
	@Autowired
	
	private PatientInfo patientInfo;

	@GetMapping("/getEmployees")
	public List<Employee> getEmployees()
{
		   File f = new File("Employee.xml");
		   if(f.exists()) {
			   f.delete();
		   }
		   else {
			   try {
				f.createNewFile();
				System.out.println("File created to list out all employees"+f.getAbsolutePath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   }
		  try {
			  JAXBContext jaxB = JAXBContext.newInstance(EmployeeList.class);
			Marshaller marshaller =jaxB.createMarshaller();
			 marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, true);
			try {
				empfile.createNewFile();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			Optional<List<Employee>> opEmp = Optional.ofNullable((List<Employee>)employeeRepository.findAll());
		        System.out.println(opEmp.get());   
			EmployeeList emplist =  new EmployeeList();
			emplist.setEmployee(opEmp.isPresent()?opEmp.get():null);
			marshaller.marshal(emplist,empfile);
		    
		  
		  } catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
				  
		   
		   
		 return employeeRepository.findAll()!=null? (List<Employee>) employeeRepository.findAll():null;
}
	
	@PostMapping("/save")
	public  ResponseEntity<Employee> saveMapping( @RequestBody Employee emp) throws IOException {
		Employee emp1 = null;
		if(emp!= null) {
			 emp1= employeeRepository.save(emp);
			 try {
				 File f = new File("Employee.xml");
				 try {
					f.createNewFile();
				System.out.println("File Created SuccessFully at "+f.getAbsolutePath());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				JAXBContext jaxb =JAXBContext.newInstance(Employee.class);
				Marshaller marshaller = jaxb.createMarshaller();
				marshaller.marshal(emp,f);
			   try {
				URL url = new URL("http://localhost:8086/getEmployees");
				
				HttpURLConnection httpURLConnection = (HttpURLConnection)(url.openConnection());
				
				httpURLConnection.setRequestMethod("GET");
				httpURLConnection.setDoOutput(true);
				
				System.out.println("actual response"+httpURLConnection.getInputStream());
				
				BufferedReader buff = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
			//	System.out.println("end ss"+buff.readLine());

				
				StringBuilder sb =new StringBuilder("{");
				if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
					
					
					sb.append(buff.readLine()+"}");
					
					
				}
				
				System.out.println("response patients "+sb.toString());
				
				
				
				
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   

			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ResponseEntity.ok(emp1);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@PostMapping("/savealsoPatient")
	public ResponseEntity<Employee> saveAlsoPatient(@RequestBody Employee employee){
		
		Employee emp1 = employeeRepository.save(employee);
         URL url = null; 
         HttpURLConnection httpURLConnection = null;
		try {
			url = new URL("http://localhost:8086/savePatientEmployee");
		
		     httpURLConnection =(HttpURLConnection)(url.openConnection());
		     
		     httpURLConnection.setDoInput(true);
		     
		     httpURLConnection.setDoOutput(true);
		     
		     httpURLConnection.setRequestMethod("POST");
		     httpURLConnection.setRequestProperty("Content-Type", "application/json");
		     httpURLConnection.setRequestProperty("Accept", "application/json");
		     
		     OutputStream os = httpURLConnection.getOutputStream();
		     
		     patientInfo.setPatientName(employee.getEmployeeName());
		     JsonObject object = new JsonObject();
		     
		     object.addProperty("name", patientInfo.getPatientName());
		     
		     //   object.addProperty("patientId", patientInfo.getPatientId());
		  //   object.addProperty("DateOfRegistration", patientInfo.getDateOfRegitstration());
		     
		     
		     System.out.println(" actual Json String " +object.toString());
		     os.write(object.toString().getBytes());
		     
		     InputStream ios =  httpURLConnection.getInputStream();
		     
		     BufferedReader b = new BufferedReader(new InputStreamReader(ios));
		     
		     StringBuilder sb = new StringBuilder("");
		     
		     if(httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
		    	 sb.append(b.readLine());
		    	 System.out.println("Updated Successfully "+ httpURLConnection.getResponseCode());
		     }

		     RestTemplate rest = new RestTemplate();
		    
		    patientInfo.setPatientName("Lddu");

            ResponseEntity<PatientInfo> restPatient  = rest.postForEntity("http://localhost:8086/savePatientEmployee", patientInfo, PatientInfo.class);
		     
            
             System.out.println("Updated through Rest Template "+restPatient.getBody());
             
             ResponseEntity<PatientInfo> patientup  = rest.getForEntity("http://localhost:8086/getPatient/Lddu",PatientInfo.class );
 		    System.out.println("Output is  "+patientup.getBody());
 		    
		     
		     return new ResponseEntity<Employee>(emp1,HttpStatus.ACCEPTED);
		     
		     
		     
		     
		     
		} catch (Exception  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		return new ResponseEntity<Employee>(emp1,HttpStatus.OK);
		//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		
	}
	
	@GetMapping("/getEmployee/{employeeid}")
	
	public Optional<Employee> getEmployeeBasedOnId(@PathVariable(name="employeeid") int id) {
		
		try {
			Unmarshaller u = jaxb.createUnmarshaller();
		Employee emp=	(Employee)u.unmarshal(new File("Employee.xml"));
		System.out.println(emp);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return employeeRepository.findById(id);
		
	}
	
	@PutMapping("/updateEmployee")
	public int updateEmployeeInfo(@RequestBody Employee emp)
	{
	//	return employeeRepository.updateinfo(emp.getId());
		return employeeRepository.updateinfo(emp.getId());
		
	}
	@SuppressWarnings("removal")
	@DeleteMapping("deleteEmployee")
	public void deleteEmployeeRecord(@RequestParam(name ="id") int employeeId) {
		
		employeeRepository.deleteById(new Integer(employeeId));
	}
	
	@GetMapping("/getSpecificEmployee")
	public List<Employee> getEmployeeDetail(@RequestParam(name = "employeeName") String  employeeName) {
		
		List<Employee> emp = employeeRepository.getEmployeeDetail(employeeName);
		System.out.println(" list fetched "+ emp);
		return emp;
	}
	
	
	
	
}
