package com.effs.estoque.services;

import java.util.Optional;

import com.effs.estoque.domain.Produto;

/**
 * @author eduardosatyra
 *
 */
public interface ProdutoService {

	Optional<Produto> buscar(Long id);
}
