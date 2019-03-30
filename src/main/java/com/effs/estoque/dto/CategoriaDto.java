package com.effs.estoque.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.effs.estoque.domain.Categoria;
import com.effs.estoque.domain.Produto;

/**
 * @author eduardosatyra
 *
 */
public class CategoriaDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 80, message = "O tamanho deve ser entra 5 e 80 caracteres")
	private String nome;
	
	private List<Produto> produtos = new ArrayList<>();
	
	public CategoriaDto() {
	}

	public CategoriaDto(Categoria c) {
		this.id = c.getId();
		this.nome = c.getNome();
		this.getProdutos().addAll(c.getProdutos());
	}	

	public CategoriaDto(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
}
