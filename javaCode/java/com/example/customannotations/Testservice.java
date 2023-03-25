package com.example.customannotations;

import org.springframework.stereotype.Service;

@Service
public class Testservice{

	
	public String getSomething() {
		String res  ="Iam Something";
		
		return res;
	}
}
