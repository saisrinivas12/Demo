package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import com.example.customannotations.Testservice;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.Controllers","com.example.jpa","com.example.demo.aspects","com.example.customannotations"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext conf = SpringApplication.run(DemoApplication.class, args);
		System.out.println(" available beans "+conf.getBeansWithAnnotation(Service.class));
		
		Testservice test = conf.getBean("testservice",Testservice.class);
		
	System.out.println(" print some "+test.getSomething());
}
}