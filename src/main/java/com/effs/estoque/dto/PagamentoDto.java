package com.effs.estoque.dto;

import java.io.Serializable;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.effs.estoque.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author eduardosatyra
 *
 */
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class PagamentoDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer estado;

	public PagamentoDto() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}
}
