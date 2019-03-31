package com.effs.estoque.services.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.effs.estoque.domain.Categoria;
import com.effs.estoque.domain.Produto;
import com.effs.estoque.dto.ProdutoDto;
import com.effs.estoque.repositories.CategoriaRepository;
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
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public Produto find(Integer id) {
		Optional<Produto> p = this.produtoRepository.findById(id);
		return p.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado. Id: " + id + ", Tipo: " + Produto.class.getName()));
	}

	@Override
	public List<Produto> findAll() {
		List<Produto> list = this.produtoRepository.findAll();
		if (list.isEmpty()) {
			throw new ObjectNotFoundException("Nenhum produto encontrado.");
		}
		return list;
	}

	@Override
	public Page<ProdutoDto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> listC = this.categoriaRepository.findAllById(ids);
		String nomeDecoded = this.decodeParam(nome);
		Page<Produto> pageList = this.produtoRepository.findDistinctByNomeIgnoreCaseContainingAndCategoriasIn(nomeDecoded, listC, pageRequest);
		if (pageList.isEmpty()) {
			throw new ObjectNotFoundException("Nenhum produto encontrado para os parametros fornecidos.");
		}
		Page<ProdutoDto> pageDto = pageList.map(produto -> new ProdutoDto(produto));
		return pageDto;
	}
	
	private String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	} 
}
