package com.effs.estoque.services;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.effs.estoque.domain.Cliente;
import com.effs.estoque.dto.ClienteDto;
import com.effs.estoque.dto.ClienteNewDto;

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

	Cliente findComplete(Integer id);

	ClienteNewDto insert(ClienteNewDto cDto);
	
	URI uploadProfilePicture(MultipartFile multipartFile);
}
