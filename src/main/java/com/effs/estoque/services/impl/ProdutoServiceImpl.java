package com.effs.estoque.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effs.estoque.domain.Produto;
import com.effs.estoque.repositories.ProdutoRepository;
import com.effs.estoque.services.ProdutoService;
import com.effs.estoque.services.exception.ObjectNotFoundException;

/**
 * @author eduardosatyra
 *
 */

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public Produto find(Integer id) {
		Optional<Produto> p = this.produtoRepository.findById(id);
		return p.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado. Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

}
