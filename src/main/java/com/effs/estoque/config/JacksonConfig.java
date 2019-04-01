package com.effs.estoque.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.effs.estoque.dto.PagamentoComBoletoDto;
import com.effs.estoque.dto.PagamentoComCartaoDto;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author eduardosatyra
 *
 */
@Configuration
public class JacksonConfig {

	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PagamentoComCartaoDto.class);
				objectMapper.registerSubtypes(PagamentoComBoletoDto.class);
				super.configure(objectMapper);
			};
		};
		return builder;
	}
}
