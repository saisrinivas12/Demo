package com.example.customannotations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomEmailValidator implements ConstraintValidator<EmailValidation,String> {

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		
		System.out.println("email to be matched" +email);
	   String regex = "^[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\\.[a-zA-Z0-9]+)*$";
	
	   
	   Pattern p = Pattern.compile(regex);
	   Matcher m = p.matcher(email);
	   return m.matches();
	   
	}

	
}
