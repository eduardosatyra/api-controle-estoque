package com.effs.estoque.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effs.estoque.domain.Cliente;
import com.effs.estoque.repositories.ClienteRepository;
import com.effs.estoque.services.ClienteService;
import com.effs.estoque.services.exception.ObjectNotFoundException;

/**
 * @author eduardosatyra
 *
 */
@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public Cliente find(Integer id) {
		Optional<Cliente> c = this.clienteRepository.findById(id);

		return c.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado. Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

}
