package com.effs.estoque.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effs.estoque.domain.Produto;
import com.effs.estoque.repositories.ProdutoRepository;
import com.effs.estoque.services.ProdutoService;

/**
 * @author eduardosatyra
 *
 */

@Service
public class ProdutoServiceImpl implements ProdutoService{

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Override
	public Optional<Produto> buscar(Long id) {
		Optional<Produto> p = this.produtoRepository.findById(id);
		return p;
	}

}
