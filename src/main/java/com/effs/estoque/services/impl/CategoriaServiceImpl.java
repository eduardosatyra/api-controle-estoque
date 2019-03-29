package com.effs.estoque.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effs.estoque.domain.Categoria;
import com.effs.estoque.repositories.CategoriaRepository;
import com.effs.estoque.services.CategoriaService;
import com.effs.estoque.services.exception.ObjectNotFoundException;

/**
 * @author eduardosatyra
 *
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public Categoria find(Integer id) {
		Optional<Categoria> c = this.categoriaRepository.findById(id);

		return c.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado. Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

}
