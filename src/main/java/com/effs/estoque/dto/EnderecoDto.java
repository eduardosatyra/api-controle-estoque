package com.effs.estoque.dto;

import java.io.Serializable;

/**
 * @author eduardosatyra
 *
 */
public class EnderecoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	private CidadeDto cidade;

	public EnderecoDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public CidadeDto getCidade() {
		return cidade;
	}

	public void setCidade(CidadeDto cidade) {
		this.cidade = cidade;
	}
}
