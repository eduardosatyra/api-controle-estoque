package com.effs.estoque;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.effs.estoque.domain.Categoria;
import com.effs.estoque.domain.Cidade;
import com.effs.estoque.domain.Cliente;
import com.effs.estoque.domain.Endereco;
import com.effs.estoque.domain.Estado;
import com.effs.estoque.domain.Produto;
import com.effs.estoque.domain.enums.TipoCliente;
import com.effs.estoque.repositories.CategoriaRepository;
import com.effs.estoque.repositories.CidadeRepository;
import com.effs.estoque.repositories.ClienteRepository;
import com.effs.estoque.repositories.EnderecoRepository;
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
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Produto prod1 = new Produto(null, "Computador", 2000.0);
		Produto prod2 = new Produto(null, "Impressora", 300.0);
		Produto prod3 = new Produto(null, "Mouse", 80.0);
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));
		
		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));		
		this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		this.produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));
		
		Estado e1 = new Estado(null, "São Paulo");
		Estado e2 = new Estado(null, "Rio de Janeiro");
		Estado e3 = new Estado(null, "Goias");
		
		Cidade cd1 = new Cidade(null, "São Paulo", e1);
		Cidade cd2 = new Cidade(null, "Botafogo", e2);
		Cidade cd3 = new Cidade(null, "Itapaci", e3);
		
		e1.getCidades().addAll(Arrays.asList(cd1));
		e2.getCidades().addAll(Arrays.asList(cd2));
		e3.getCidades().addAll(Arrays.asList(cd3));
		
		this.estadoRepository.saveAll(Arrays.asList(e1, e2, e3));
		this.cidadeRepository.saveAll(Arrays.asList(cd1, cd2, cd3));
		
		Cliente cli1 = new Cliente(null, "Jose Farias", "57832532009", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("11111111111"));
		Cliente cli2 = new Cliente(null, "Renata Goncalves", "98443547065", TipoCliente.PESSOAFISICA);
		cli2.getTelefones().addAll(Arrays.asList("11222222222"));
		Cliente cli3 = new Cliente(null, "Raimunda Nonota", "65708207000160", TipoCliente.PESSOAJURIDICA);
		cli3.getTelefones().addAll(Arrays.asList("11333333333"));
		
		Endereco end1Cli1 = new Endereco(null, "Rua teste", "46", "Casa amarela", "Jd teste", "03535-000", cd1, cli1);
		Endereco end2Cli1 = new Endereco(null, "Rua teste Old", "64", "Casa amarela Old", "Jd teste Old", "03535-050", cd1, cli1);
		Endereco end3Cli2 = new Endereco(null, "Rua teste cliente 2", "4", "Casa Azul", "Jd teste cli 2", "02222-000", cd2, cli2);
		Endereco end4Cli3= new Endereco(null, "Rua teste cliente 3", "6", "Casa Verde", "Jd teste cli 3", "33333-000", cd3, cli3);
		cli1.getEnderecos().addAll(Arrays.asList(end1Cli1, end2Cli1, end3Cli2, end4Cli3));
		cli2.getEnderecos().addAll(Arrays.asList(end3Cli2));
		cli3.getEnderecos().addAll(Arrays.asList(end4Cli3));
		
		this.clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
		this.enderecoRepository.saveAll(Arrays.asList(end1Cli1, end2Cli1, end3Cli2, end4Cli3));
	}

}
