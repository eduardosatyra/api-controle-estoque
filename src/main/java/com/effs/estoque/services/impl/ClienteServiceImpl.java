package com.effs.estoque.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effs.estoque.domain.Cliente;
import com.effs.estoque.domain.enums.TipoCliente;
import com.effs.estoque.dto.ClienteDto;
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
	public ClienteDto find(Integer id) {
		Optional<Cliente> c = this.clienteRepository.findById(id);
		if (!c.isPresent()) {
			throw new ObjectNotFoundException("Cliente não encontrado pelo id: " + id + ", Objeto :" + Cliente.class.getName()); 
		}
		return this.parseEntityToDto(c.get());
	}
	
	public Cliente parseDtoToEntity(ClienteDto cDto) {
		Cliente c = new Cliente(cDto.getId(), cDto.getNome(), cDto.getCpfOuCnpj(), TipoCliente.toEnum(cDto.getTipo()));		
		return c;
	}
	
	public ClienteDto parseEntityToDto(Cliente c) {
		ClienteDto cDto = new ClienteDto(c);
		return cDto;
	}
}
