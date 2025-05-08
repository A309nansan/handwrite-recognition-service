package com.nansan.handwrite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HandwriteRecognitionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HandwriteRecognitionServiceApplication.class, args);
	}

}
