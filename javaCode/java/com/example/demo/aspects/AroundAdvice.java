package com.example.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AroundAdvice {

	//@Around(value ="execution(* com.example.Controllers.FriendController.*(..))")
	public void aroundAdvice(JoinPoint jp) {
		
		System.out.println("Executing Around advice with signature "+jp.getSignature().getName());
	}
	
	
}
