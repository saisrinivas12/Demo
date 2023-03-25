package com.example.demo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BeforeAspect {

	//@Before(value = "execution(* com.example.Controllers.FriendController.*(..))")
	public void beforeAdvice(JoinPoint jp) {
		System.out.println("executing before advice "+jp.getSignature());
		
	}
}
