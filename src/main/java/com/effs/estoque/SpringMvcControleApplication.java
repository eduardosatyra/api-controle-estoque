package com.effs.estoque;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.effs.estoque.domain.Categoria;
import com.effs.estoque.repositories.CategoriaRepository;

@SpringBootApplication
public class SpringMvcControleApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcControleApplication.class, args);
	}

	@Autowired
	private CategoriaRepository catRep;
	
	@Override
	public void run(String... args) throws Exception {
		
		Categoria c1 = new Categoria(1L, "Informática");
		Categoria c2 = new Categoria(2L, "Escritório");		
		this.catRep.saveAll(Arrays.asList(c1, c2));
	}

}
