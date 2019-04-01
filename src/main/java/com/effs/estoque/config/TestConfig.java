package com.effs.estoque.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.effs.estoque.services.DBService;

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
}
