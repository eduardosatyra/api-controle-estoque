package com.effs.estoque.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.effs.estoque.dto.ClienteDto;

/**
 * @author eduardosatyra
 *
 */
public interface ClienteService {

	ClienteDto find(Integer id);

	List<ClienteDto> findAll();
	
	Page<ClienteDto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);

	ClienteDto update(ClienteDto cDto);

	void delete(Integer id);
}
