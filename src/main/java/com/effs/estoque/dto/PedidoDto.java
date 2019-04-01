package com.effs.estoque.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author eduardosatyra
 *
 */
public class PedidoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Date instante;
	private PagamentoDto pagamento;
	private ClienteDto cliente;
	private EnderecoDto enderecoDeEntrega;
	private Set<ItemPedidoDto> items = new HashSet<>();

	public PedidoDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public PagamentoDto getPagamento() {
		return pagamento;
	}

	public void setPagamento(PagamentoDto pagamento) {
		this.pagamento = pagamento;
	}

	public ClienteDto getCliente() {
		return cliente;
	}

	public void setCliente(ClienteDto cliente) {
		this.cliente = cliente;
	}

	public EnderecoDto getEnderecoDeEntrega() {
		return enderecoDeEntrega;
	}

	public void setEnderecoDeEntrega(EnderecoDto enderecoDeEntrega) {
		this.enderecoDeEntrega = enderecoDeEntrega;
	}

	public Set<ItemPedidoDto> getItems() {
		return items;
	}

	public void setItems(Set<ItemPedidoDto> items) {
		this.items = items;
	}
}
