package com.effs.estoque.services;

import java.util.Optional;

import com.effs.estoque.domain.Categoria;

/**
 * @author eduardosatyra
 *
 */
public interface CategoriaService {

	Optional<Categoria> buscar(Long id);
}
