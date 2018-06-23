package com.webdev.siteparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SiteparserApplication {


	public static void main(String[] args) {
 		SpringApplication.run(SiteparserApplication.class, args);
	}

}
