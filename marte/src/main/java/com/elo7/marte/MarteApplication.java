package com.elo7.marte;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.elo7.marte")
public class MarteApplication{

	public static void main(String[] args) throws Exception {
		SpringApplication.run(MarteApplication.class, args);
	}
}
