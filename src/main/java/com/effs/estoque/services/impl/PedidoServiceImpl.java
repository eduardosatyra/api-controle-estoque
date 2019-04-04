package com.effs.estoque.services.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.effs.estoque.domain.Cliente;
import com.effs.estoque.domain.Endereco;
import com.effs.estoque.domain.ItemPedido;
import com.effs.estoque.domain.Pagamento;
import com.effs.estoque.domain.PagamentoComBoleto;
import com.effs.estoque.domain.PagamentoComCartao;
import com.effs.estoque.domain.Pedido;
import com.effs.estoque.domain.Produto;
import com.effs.estoque.domain.enums.EstadoPagamento;
import com.effs.estoque.dto.ItemPedidoDto;
import com.effs.estoque.dto.PagamentoComBoletoDto;
import com.effs.estoque.dto.PagamentoComCartaoDto;
import com.effs.estoque.dto.PedidoDto;
import com.effs.estoque.repositories.EnderecoRepository;
import com.effs.estoque.repositories.ItemPedidoRepository;
import com.effs.estoque.repositories.PagamentoRepository;
import com.effs.estoque.repositories.PedidoRepository;
import com.effs.estoque.services.ClienteService;
import com.effs.estoque.services.PedidoService;
import com.effs.estoque.services.ProdutoService;
import com.effs.estoque.services.exception.ObjectNotFoundException;

/**
 * @author eduardosatyra
 *
 */
@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ProdutoService produtoService;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Override
	public Pedido find(Integer id) {
		Optional<Pedido> p = this.pedidoRepository.findById(id);
		return p.orElseThrow(() -> new ObjectNotFoundException(
				"Pedido não encontrado pelo id: " + id + ", Objeto: " + Pedido.class.getName()));
	}

	@Transactional
	@Override
	public Pedido insert(PedidoDto pDto) {
		Pedido p = this.parseDtoToEntity(pDto);
		return p;
	}

	
	private Pedido parseDtoToEntity(PedidoDto pDto) {
		Pedido p = new Pedido();
		p.setInstante(new Date());
		
		Cliente c = this.clienteService.findComplete(pDto.getCliente().getId());
		p.setCliente(c);
		
		Optional<Endereco> e = this.enderecoRepository.findById(pDto.getEnderecoDeEntrega().getId());
		if (!e.isPresent()) {
			throw new ObjectNotFoundException("Pedido não inserido, Endereco de entrega não encontrado pelo id: " + pDto.getCliente().getId());
		}
		p.setEnderecoDeEntrega(e.get());
		
		if (pDto.getPagamento() instanceof PagamentoComBoletoDto) {			
			Pagamento pagto = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, p, this.calculaVencimentoBoletoDto(p.getInstante()), null);
			p.setPagamento(pagto);
		} else {
			PagamentoComCartaoDto pagtoDto = (PagamentoComCartaoDto) pDto.getPagamento();
			Pagamento pagto = new PagamentoComCartao(null, EstadoPagamento.PENDENTE, p, pagtoDto.getNumeroParcelas());
			p.setPagamento(pagto);
		}
		
		p = this.pedidoRepository.save(p);
		this.pagamentoRepository.save(p.getPagamento());
		
		for (ItemPedidoDto item : pDto.getItems()) {
			Produto prod = this.produtoService.find(item.getProduto().getId());
			ItemPedido i = new ItemPedido();
			i.getId().setProduto(prod);
			i.getId().setPedido(p);
			i.setPreco(prod.getPreco());
			i.setQuantidade(item.getQuantidade());
			i.setDesconto(0.0);
			p.getItems().add(i);
		}
		itemPedidoRepository.saveAll(p.getItems());
		System.out.println(p);
		return p;
	}

	private Date calculaVencimentoBoletoDto(Date instantePedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instantePedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		return cal.getTime();
	}
}
