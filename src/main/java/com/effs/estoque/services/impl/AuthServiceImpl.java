package com.effs.estoque.services.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.effs.estoque.domain.Cliente;
import com.effs.estoque.repositories.ClienteRepository;
import com.effs.estoque.services.AuthService;
import com.effs.estoque.services.EmailService;
import com.effs.estoque.services.exception.ObjectNotFoundException;

/**
 * @author eduardosatyra
 *
 */
@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private BCryptPasswordEncoder bCrypt;
	@Autowired
	private EmailService emailService;
	
	private Random random = new Random();

	public void sendNewPassword(String email){
		Cliente c = this.clienteRepository.findByEmail(email);
		if (c == null) {
			throw new ObjectNotFoundException("Cliente não encontrado pelo e-mail: " + email);
		}
		String senha = novaSenha();
		c.setSenha(bCrypt.encode(senha));
		
		this.clienteRepository.save(c);
		
		this.emailService.sendNewPasswordEmail(c, senha);
		
	}

	private String novaSenha() {
		char[] vet = new char[10];
		for (int i =0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		if (opt == 0) { //gera digito
			return (char) (random.nextInt(10) + 48);
		} else if (opt == 1) { //gera letra maiuscula
			return (char) (random.nextInt(26) + 65);
		} else { //gera letra miniscula
			return (char) (random.nextInt(26) + 97);
		}
	}
}
