package com.effs.estoque.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.effs.estoque.services.DBService;
import com.effs.estoque.services.EmailService;
import com.effs.estoque.services.SmtpEmailService;

/**
 * @author eduardosatyra
 *
 */
@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService db;

	@Bean
	public boolean instantiateDataBase() throws Exception {
		db.instantiateTestDataBase();
		return true;
	}
	
//	para mockar o envio de e-mail	
//	@Bean
//	public MockEmailService emailService() {
//		return new MockEmailService();
//	}
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
