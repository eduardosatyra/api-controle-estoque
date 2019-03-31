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
	private CidadeDto cidadeDto;
	private ClienteDto clienteDto;
}
