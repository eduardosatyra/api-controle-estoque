package com.effs.estoque.dto;

import java.io.Serializable;

/**
 * @author eduardosatyra
 *
 */
public class ItemPedidoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Double desconto;
	private Integer quantidade;
	private Double preco;
	private ProdutoDto produto;
	private PedidoDto pedido;

	public ItemPedidoDto() {
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public ProdutoDto getProduto() {
		return produto;
	}

	public void setProduto(ProdutoDto produto) {
		this.produto = produto;
	}

	public PedidoDto getPedido() {
		return pedido;
	}

	public void setPedido(PedidoDto pedido) {
		this.pedido = pedido;
	}
}
