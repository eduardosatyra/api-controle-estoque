package com.effs.estoque;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.effs.estoque.domain.Categoria;
import com.effs.estoque.domain.Cidade;
import com.effs.estoque.domain.Estado;
import com.effs.estoque.domain.Produto;
import com.effs.estoque.repositories.CategoriaRepository;
import com.effs.estoque.repositories.CidadeRepository;
import com.effs.estoque.repositories.EstadoRepository;
import com.effs.estoque.repositories.ProdutoRepository;

@SpringBootApplication
public class SpringMvcControleApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcControleApplication.class, args);
	}

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 300.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);
		
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Escritório");
		
		c1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		c2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1, c2));
		p3.getCategorias().addAll(Arrays.asList(c1));		
		this.categoriaRepository.saveAll(Arrays.asList(c1, c2));
		this.produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado e1 = new Estado(null, "São Paulo");
		Estado e2 = new Estado(null, "Rio de Janeiro");
		Estado e3 = new Estado(null, "Goias");
		
		Cidade cd1 = new Cidade(null, "São Paulo", e1);
		Cidade cd2 = new Cidade(null, "Botafogo", e2);
		Cidade cd3 = new Cidade(null, "Itapaci", e3);
		
		e1.getCidade().addAll(Arrays.asList(cd1));
		e2.getCidade().addAll(Arrays.asList(cd2));
		e3.getCidade().addAll(Arrays.asList(cd3));
		
		this.estadoRepository.saveAll(Arrays.asList(e1, e2, e3));
		this.cidadeRepository.saveAll(Arrays.asList(cd1, cd2, cd3));
	}

}
