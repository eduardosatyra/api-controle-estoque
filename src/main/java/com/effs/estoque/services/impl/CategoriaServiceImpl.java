package com.effs.estoque.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.effs.estoque.domain.Categoria;
import com.effs.estoque.dto.CategoriaDto;
import com.effs.estoque.repositories.CategoriaRepository;
import com.effs.estoque.services.CategoriaService;
import com.effs.estoque.services.exception.DataIntegrityException;
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
	public CategoriaDto find(Integer id) {
		Optional<Categoria> c = this.categoriaRepository.findById(id);
		if (!c.isPresent()) {
			throw new ObjectNotFoundException(
					"Objeto não encontrado. Id: " + id + ", Tipo: " + Categoria.class.getName());
		}
		return this.parseEntityToDto(c.get());
	}

	@Override
	public CategoriaDto insert(CategoriaDto cDto) {
		Categoria c = this.parseDtoToEntity(cDto);
		c.setId(null);
		c = this.categoriaRepository.saveAndFlush(c);
		return this.parseEntityToDto(c);
	}

	@Override
	public CategoriaDto update(CategoriaDto cDto) {
		Categoria c = this.parseDtoToEntity(cDto);
		cDto = this.find(cDto.getId());
		c = this.categoriaRepository.saveAndFlush(c);
		return cDto = this.parseEntityToDto(c);
	}

	@Override
	public void delete(Integer id) {
		CategoriaDto cDto = this.find(id);
		Categoria c = this.parseDtoToEntity(cDto);
		try {
			this.categoriaRepository.delete(c);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Existem Produtos relácionados a essa categoria, não foi possível deletar");
		}
	}

	public Categoria parseDtoToEntity(CategoriaDto cDto) {
		Categoria c = new Categoria(cDto.getId(), cDto.getNome());
		return c;
	}

	public CategoriaDto parseEntityToDto(Categoria c) {
		CategoriaDto cDto = new CategoriaDto(c);
		return cDto;
	}
}
