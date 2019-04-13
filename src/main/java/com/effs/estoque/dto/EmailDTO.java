package com.effs.estoque.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * @author eduardosatyra
 *
 */
public class EmailDTO {

	@NotEmpty(message = "Preenchimento obrigatorio")
	@Email(message = "E-mail invalido")
	private String email;

	public EmailDTO() {
	}

	public EmailDTO(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
