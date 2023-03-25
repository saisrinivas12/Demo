package com.example.Controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entities.Friend;
import com.example.entities.Smartphone;
import com.example.jpa.FriendRepository;

@EntityScan(basePackages = "com.example.entities")
@RestController
@EnableJpaRepositories
@PropertySource("classpath:SmartPhone.properties")
public class FriendController {
	@Autowired
	
	private FriendRepository friendRepo;
	
	@Autowired
	private Optional<Properties> mobileProperties;
	

	@PostMapping("/saveFriend")
	public ResponseEntity<Friend> saveFriend(@RequestBody Friend friend){
		//String id = mobileProperties.isPresent()? mobileProperties.get().getProperty("mobileId"):new String("-1");
		
			System.out.println(" Aop test "+friend.getFriendName());
		Exception ex = null;
		if(friend.getFriendName() == null) {
			
			ex = new Exception("Friend name cannot be Null");
			return new ResponseEntity<Friend>(HttpStatus.PRECONDITION_FAILED);
		}
	   Smartphone phn = new Smartphone();
	   phn.setMobileId(1);
	   phn.setMobileName("samsung");
	   phn.setMobilePrice(20000);
	   Smartphone phn2 = new Smartphone();
	   phn2.setMobileId(2);
	   phn2.setMobileName("samsung");
	   phn2.setMobilePrice(20004);
	   List<Smartphone> al = new ArrayList<Smartphone>();
	     al.add(phn);
	     al.add(phn2);
		 friend.setSmartPhone(al);
		  Friend fr = friendRepo.save(friend);
		Optional<Friend> op = Optional.of(fr);
		InputStream in =null;
		/*try {
			 in = new FileInputStream(new File("classpath:SmartPhone.properties"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        System.out.println("input stream "+in);
		*/
		ResponseEntity<Friend> res = new ResponseEntity<Friend>(op.isPresent()?op.get():new Friend(),HttpStatus.ACCEPTED);
		System.out.println(" Response Returned "+res);
		return res;
	}
	
	
}
