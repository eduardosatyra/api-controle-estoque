package com.effs.estoque.services;

import com.effs.estoque.dto.CategoriaDto;

/**
 * @author eduardosatyra
 *
 */
public interface CategoriaService {

	CategoriaDto find(Integer id);
	
	CategoriaDto insert(CategoriaDto cDto);
	
	CategoriaDto update(CategoriaDto cDto);
}
