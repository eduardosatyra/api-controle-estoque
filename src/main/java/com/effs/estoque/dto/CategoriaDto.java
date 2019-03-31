package com.effs.estoque.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.effs.estoque.domain.Categoria;

/**
 * @author eduardosatyra
 *
 */
public class CategoriaDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 3, max = 80, message = "O tamanho deve ser entra 5 e 80 caracteres")
	private String nome;
	
	public CategoriaDto() {
	}

	public CategoriaDto(Categoria c) {
		this.id = c.getId();
		this.nome = c.getNome();
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
}
