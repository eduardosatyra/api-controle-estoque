package com.effs.estoque.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.effs.estoque.domain.Cliente;
import com.effs.estoque.dto.ClienteDto;
import com.effs.estoque.repositories.ClienteRepository;
import com.effs.estoque.services.ClienteService;
import com.effs.estoque.services.exception.DataIntegrityException;
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
			throw new ObjectNotFoundException(
					"Cliente não encontrado pelo id: " + id + ", Objeto :" + Cliente.class.getName());
		}
		return this.parseEntityToDto(c.get());
	}

	@Override
	public List<ClienteDto> findAll() {
		List<Cliente> cList = this.clienteRepository.findAll();
		if (cList.isEmpty()) {
			throw new ObjectNotFoundException("Nenhum cliente encontrado.");
		}
		List<ClienteDto> cDtoList = cList.stream().map(c -> new ClienteDto(c)).collect(Collectors.toList());
		return cDtoList;
	}

	@Override
	public Page<ClienteDto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Cliente> pageList = this.clienteRepository.findAll(pageRequest);
		if (pageList.isEmpty()) {
			throw new ObjectNotFoundException("Nenhum cliente encontrada.");
		}
		Page<ClienteDto> pageDto = pageList.map(c -> new ClienteDto(c));
		return pageDto;
	}

	@Override
	public ClienteDto update(ClienteDto cDto) {
		Cliente c = this.clienteRepository.getOne(cDto.getId());
		if (c == null) {
			throw new ObjectNotFoundException("Nenhum cliente encontrado para o id: " + cDto.getId());
		}
		updateData(cDto, c);
		c = this.clienteRepository.saveAndFlush(c);
		return cDto = this.parseEntityToDto(c);
	}

	@Override
	public void delete(Integer id) {
		ClienteDto cDto = this.find(id);
		Cliente c = this.parseDtoToEntity(cDto);
		try {
			this.clienteRepository.delete(c);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível excluir porque há entidades relacionadas.");
		}
	}

	public Cliente parseDtoToEntity(ClienteDto cDto) {
		return new Cliente(cDto.getId(), cDto.getNome(), cDto.getEmail(), null, null);
	}

	public ClienteDto parseEntityToDto(Cliente c) {
		ClienteDto cDto = new ClienteDto(c);
		return cDto;
	}

	private void updateData(ClienteDto cDto, Cliente c) {
		c.setNome(cDto.getNome());
		c.setEmail(cDto.getEmail());
	}
}
