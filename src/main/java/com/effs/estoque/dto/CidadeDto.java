package com.effs.estoque.dto;

import java.io.Serializable;

/**
 * @author eduardosatyra
 *
 */
public class CidadeDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private EstadoDto estado;

	public CidadeDto() {
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

	public EstadoDto getEstado() {
		return estado;
	}

	public void setEstado(EstadoDto estado) {
		this.estado = estado;
	}
}
