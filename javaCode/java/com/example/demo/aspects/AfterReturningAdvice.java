package com.example.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.entities.Friend;

@Aspect
@Component

public class AfterReturningAdvice {

	@AfterReturning(value = "execution(* com.example.customannotations.Testservice.*(..))",returning = "res")
	public void afterReturningadvice(JoinPoint jp,ResponseEntity<Friend> res) {
		
		System.out.println("JoinPoint Signature in afterreturning advice"+jp.getSignature().getName());
		//System.out.println(" The value that has been returned "+res.getBody()+"{ with status code "+res.getStatusCode().toString());
	}
	
    
}
