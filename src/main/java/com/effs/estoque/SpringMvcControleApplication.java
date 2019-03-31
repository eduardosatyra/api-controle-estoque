package com.effs.estoque;

import java.text.SimpleDateFormat;
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
import com.effs.estoque.domain.ItemPedido;
import com.effs.estoque.domain.Pagamento;
import com.effs.estoque.domain.PagamentoComBoleto;
import com.effs.estoque.domain.PagamentoComCartao;
import com.effs.estoque.domain.Pedido;
import com.effs.estoque.domain.Produto;
import com.effs.estoque.domain.enums.EstadoPagamento;
import com.effs.estoque.domain.enums.TipoCliente;
import com.effs.estoque.repositories.CategoriaRepository;
import com.effs.estoque.repositories.CidadeRepository;
import com.effs.estoque.repositories.ClienteRepository;
import com.effs.estoque.repositories.EnderecoRepository;
import com.effs.estoque.repositories.EstadoRepository;
import com.effs.estoque.repositories.ItemPedidoRepository;
import com.effs.estoque.repositories.PagamentoRepository;
import com.effs.estoque.repositories.PedidoRepository;
import com.effs.estoque.repositories.ProdutoRepository;

@SpringBootApplication
public class SpringMvcControleApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringMvcControleApplication.class, args);
	}

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Produto prod1 = new Produto(null, "Computador", 2000.0);
		Produto prod2 = new Produto(null, "Impressora", 300.0);
		Produto prod3 = new Produto(null, "Mouse", 80.0);
		Produto prod4 = new Produto(null, "Mesa de escritório", 300.00);
		Produto prod5 = new Produto(null, "Toalha", 50.00);
		Produto prod6 = new Produto(null, "Colcha", 200.00);
		Produto prod7 = new Produto(null, "TV true color", 1200.00);
		Produto prod8 = new Produto(null, "Roçadeira", 800.00);
		Produto prod9 = new Produto(null, "Abajour", 100.00);
		Produto prod10 = new Produto(null, "Pendente", 180.00);
		Produto prod11 = new Produto(null, "Shampoo", 90.00);
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletronicos");
		Categoria cat5 = new Categoria(null, "Decoração");
		Categoria cat6 = new Categoria(null, "Jardinagem");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2, prod4));
		cat3.getProdutos().addAll(Arrays.asList(prod5, prod6));
		cat4.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3, prod7));
		cat5.getProdutos().addAll(Arrays.asList(prod8));
		cat6.getProdutos().addAll(Arrays.asList(prod9, prod10));
		cat7.getProdutos().addAll(Arrays.asList(prod11));
		
		prod1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		prod3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		prod4.getCategorias().addAll(Arrays.asList(cat2));
		prod5.getCategorias().addAll(Arrays.asList(cat3));
		prod6.getCategorias().addAll(Arrays.asList(cat3));
		prod7.getCategorias().addAll(Arrays.asList(cat4));
		prod8.getCategorias().addAll(Arrays.asList(cat5));
		prod9.getCategorias().addAll(Arrays.asList(cat6));
		prod10.getCategorias().addAll(Arrays.asList(cat6));
		prod11.getCategorias().addAll(Arrays.asList(cat7));
		this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
		this.produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11));
		
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
		
		Cliente cli1 = new Cliente(null, "Jose Farias", "josefarias@gmail.com", "57832532009", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("11111111111"));
		Cliente cli2 = new Cliente(null, "Renata Goncalves", "renatagoncalves@hotmail.com", "98443547065", TipoCliente.PESSOAFISICA);
		cli2.getTelefones().addAll(Arrays.asList("11222222222"));
		Cliente cli3 = new Cliente(null, "Raimunda Nonota", "raimundanonata@yahoo.com.br", "65708207000160", TipoCliente.PESSOAJURIDICA);
		cli3.getTelefones().addAll(Arrays.asList("11333333333"));
		
		Endereco end1Cli1 = new Endereco(null, "Rua teste", "46", "Casa amarela", "Jd teste", "03535-000", cd1, cli1);
		Endereco end2Cli1 = new Endereco(null, "Rua teste Old", "64", "Casa amarela Old", "Jd teste Old", "03535-050", cd1, cli1);
		Endereco end3Cli2 = new Endereco(null, "Rua teste cliente 2", "4", "Casa Azul", "Jd teste cli 2", "02222-000", cd2, cli2);
		Endereco end4Cli3= new Endereco(null, "Rua teste cliente 3", "6", "Casa Verde", "Jd teste cli 3", "33333-000", cd3, cli3);
		cli1.getEndereco().addAll(Arrays.asList(end1Cli1, end2Cli1, end3Cli2, end4Cli3));
		cli2.getEndereco().addAll(Arrays.asList(end3Cli2));
		cli3.getEndereco().addAll(Arrays.asList(end4Cli3));
		
		this.clienteRepository.saveAll(Arrays.asList(cli1, cli2, cli3));
		this.enderecoRepository.saveAll(Arrays.asList(end1Cli1, end2Cli1, end3Cli2, end4Cli3));
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1Cli1);
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pedido ped2 = new Pedido(null, sdf.parse("15/07/2015 01:32"), cli2, end3Cli2);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2018 00:00"), null);
		ped2.setPagamento(pagto2);
		
		Pedido ped3 = new Pedido(null, sdf.parse("09/08/2016 15:38"), cli3, end4Cli3);
		Pagamento pagto3 = new PagamentoComCartao(null, EstadoPagamento.CANCELADO, ped3, 2);
		ped3.setPagamento(pagto3);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1));
		cli2.getPedidos().addAll(Arrays.asList(ped2));
		cli3.getPedidos().addAll(Arrays.asList(ped3));
		
		this.pedidoRepository.saveAll(Arrays.asList(ped1, ped2, ped3));
		this.pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2, pagto3));
		
		ItemPedido ip1 = new ItemPedido(ped1, prod1, 0.0, 2, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped2, prod2, 0.0, 5, 300.0);
		ItemPedido ip3 = new ItemPedido(ped3, prod3, 0.0, 1, 80.0);
		
		ped1.getItems().addAll(Arrays.asList(ip1));
		ped2.getItems().addAll(Arrays.asList(ip2));
		ped3.getItems().addAll(Arrays.asList(ip3));
		
		prod1.getItems().addAll(Arrays.asList(ip1));
		prod2.getItems().addAll(Arrays.asList(ip2));
		prod3.getItems().addAll(Arrays.asList(ip3));
		
		this.itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}

}
