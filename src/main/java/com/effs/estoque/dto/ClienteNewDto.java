package com.effs.estoque.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.effs.estoque.services.validation.ClienteInsert;

/**
 * @author eduardosatyra
 *
 */

@ClienteInsert
public class ClienteNewDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 3, max = 80, message = "O tamanho deve ser entra 3 e 80 caracteres")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Email(message = "Email inválido")
	private String email;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String cpfOuCnpj;
	private Integer tipo;
	private List<EnderecoDto> enderecosDto = new ArrayList<>();
	
	@NotEmpty(message = "Pelo menos 1 telefone é obrigatório")
	private Set<String> telefoneDto = new HashSet<>();

	public ClienteNewDto() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<EnderecoDto> getEnderecos() {
		return enderecosDto;
	}

	public void setEnderecos(List<EnderecoDto> enderecosDto) {
		this.enderecosDto = enderecosDto;
	}

	public Set<String> getTelefones() {
		return telefoneDto;
	}

	public void setTelefones(Set<String> telefoneDto) {
		this.telefoneDto = telefoneDto;
	}
}
