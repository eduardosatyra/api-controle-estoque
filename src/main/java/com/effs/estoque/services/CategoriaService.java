package com.effs.estoque.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.effs.estoque.dto.CategoriaDto;

/**
 * @author eduardosatyra
 *
 */
public interface CategoriaService {

	CategoriaDto find(Integer id);
	
	CategoriaDto insert(CategoriaDto cDto);
	
	CategoriaDto update(CategoriaDto cDto);
	
	void delete(Integer id);
	
	List<CategoriaDto> findAll();
	
	Page<CategoriaDto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);
}
