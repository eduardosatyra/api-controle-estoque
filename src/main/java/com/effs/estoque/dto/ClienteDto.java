package com.effs.estoque.dto;

import com.effs.estoque.domain.Cliente;

/**
 * @author eduardosatyra
 *
 */
public class ClienteDto {

	private Integer id;
	private String nome;
	private String cpfOuCnpj;
	private Integer tipo;

	public ClienteDto() {
	}

	public ClienteDto(Cliente c) {
		this.id = c.getId();
		this.nome = c.getNome();
		this.cpfOuCnpj = c.getCpfOuCnpj();
		this.tipo = c.getTipo().getCod();
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
}
