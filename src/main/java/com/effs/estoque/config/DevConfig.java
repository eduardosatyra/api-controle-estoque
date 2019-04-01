package com.effs.estoque.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.effs.estoque.services.DBService;

/**
 * @author eduardosatyra
 *
 */
@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService db;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDataBase() throws Exception {
		if (!"create".equals(strategy)) {
			return false;
		}		
		db.instantiateTestDataBase();
		return true;
	}
}
