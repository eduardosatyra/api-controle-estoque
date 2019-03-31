package com.effs.estoque.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.effs.estoque.domain.Produto;
import com.effs.estoque.dto.ProdutoDto;

/**
 * @author eduardosatyra
 *
 */
public interface ProdutoService {

	Produto find(Integer id);
	
	List<Produto> findAll ();
	
	Page<ProdutoDto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction);
}
