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
import com.effs.estoque.repositories.ClienteRepository;
import com.effs.estoque.repositories.EnderecoRepository;
import com.effs.estoque.repositories.ItemPedidoRepository;
import com.effs.estoque.repositories.PagamentoRepository;
import com.effs.estoque.repositories.PedidoRepository;
import com.effs.estoque.repositories.ProdutoRepository;
import com.effs.estoque.services.PedidoService;
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
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
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
		
		Cliente c = this.clienteRepository.getOne(pDto.getCliente().getId());
		if (c == null) {
			throw new ObjectNotFoundException("Pedido não inserido, Cliente não encontrado pelo id: " + pDto.getCliente().getId());
		}
		p.setCliente(c);
		
		Endereco e = this.enderecoRepository.getOne(pDto.getEnderecoDeEntrega().getId());
		if (e == null) {
			throw new ObjectNotFoundException("Pedido não inserido, Endereco de entrega não encontrado pelo id: " + pDto.getCliente().getId());
		}
		p.setEnderecoDeEntrega(e);
		
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
			Optional<Produto> prod = this.produtoRepository.findById(item.getProduto().getId());
			if (!prod.isPresent()) {
				throw new ObjectNotFoundException("Pedido não inserido, Produto não encontrado pelo id: " + item.getProduto().getId());
			}
			ItemPedido i = new ItemPedido();
			i.getId().setProduto(prod.get());
			i.getId().setPedido(p);
			i.setPreco(prod.get().getPreco());
			i.setQuantidade(item.getQuantidade());
			i.setDesconto(0.0);
			p.getItems().add(i);
		}
		itemPedidoRepository.saveAll(p.getItems());
		return p;
	}

	private Date calculaVencimentoBoletoDto(Date instantePedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instantePedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		return cal.getTime();
	}
}
