package com.effs.estoque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.effs.estoque.services.S3Service;

@SpringBootApplication
public class SpringMvcControleApplication implements CommandLineRunner {

	@Autowired
	private S3Service s3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringMvcControleApplication.class, args);
	}

	@Override
	public void run(String... args) {
		s3Service.uploadFile("/home/eduardosatyra/Pictures/s3/1.png");
	}

}
