package com.effs.estoque.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effs.estoque.domain.Categoria;
import com.effs.estoque.dto.CategoriaDto;
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
		Categoria c = this.find(cDto.getId());
		c = this.parseDtoToEntity(cDto);
		c = this.categoriaRepository.saveAndFlush(c);
		return cDto = this.parseEntityToDto(c);
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
