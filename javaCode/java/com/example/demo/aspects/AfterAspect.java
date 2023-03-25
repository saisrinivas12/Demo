package com.example.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AfterAspect {
	
	//@After(value = "execution(* com.example.Controllers.FriendController.*(..))")
	public void afterAdvice(JoinPoint jp) {
		
		System.out.println("executing after advice "+jp.getSignature());
	}
	
	@AfterThrowing(value ="execution(* com.example.Controllers.FriendController.*(..))",throwing = "ex")
	public void afterThrowing(JoinPoint jp , Exception ex) {
		System.out.println(" exception caught is "+ex.getMessage());
	}
	
	

}
