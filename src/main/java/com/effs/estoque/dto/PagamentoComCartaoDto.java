package com.effs.estoque.dto;

import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author eduardosatyra
 *
 */
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartaoDto extends PagamentoDto {
	private static final long serialVersionUID = 1L;

	private Integer numeroParcelas;

	public PagamentoComCartaoDto() {
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	public void setNumeroParcelas(Integer numeroParcelas) {
		this.numeroParcelas = numeroParcelas;
	}
}
