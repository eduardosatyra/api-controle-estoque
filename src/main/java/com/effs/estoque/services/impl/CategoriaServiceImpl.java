package com.effs.estoque.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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
		Categoria c = this.categoriaRepository.findByNome(cDto.getNome());
		if (c != null) {
			throw new ObjectNotFoundException("FODASE TROCO DEPOIS");
		}
		c = this.parseDtoToEntity(cDto);
		c.setId(null);
		c = this.categoriaRepository.saveAndFlush(c);
		return this.parseEntityToDto(c);
	}

	@Override
	public CategoriaDto update(CategoriaDto cDto) {
		Categoria c = this.categoriaRepository.getOne(cDto.getId());
		if (c == null) {
			throw new ObjectNotFoundException("Nenhuma categoria encontrado para o id: " + cDto.getId());
		}
		updateData(cDto, c);
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

	@Override
	public List<CategoriaDto> findAll() {
		List<Categoria> cList = new ArrayList<>();
		cList = this.categoriaRepository.findAll();
		if (cList.isEmpty()) {
			throw new ObjectNotFoundException("Nenhuma categoria encontrada.");
		}
		List<CategoriaDto> cDtoList = cList.stream().map(c -> new CategoriaDto(c))
				.collect(Collectors.toList());
		return cDtoList;
	}

	@Override
	public Page<CategoriaDto> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Categoria> pageList = this.categoriaRepository.findAll(pageRequest);
		if (pageList.isEmpty()) {
			throw new ObjectNotFoundException("Nenhuma categoria encontrada.");
		}
		Page<CategoriaDto> pageDto = pageList.map(c -> new CategoriaDto(c));
		return pageDto;
	}

	public Categoria parseDtoToEntity(CategoriaDto cDto) {
		Categoria c = new Categoria(cDto.getId(), cDto.getNome());
		return c;
	}

	public CategoriaDto parseEntityToDto(Categoria c) {
		CategoriaDto cDto = new CategoriaDto(c);
		return cDto;
	}

	private void updateData(CategoriaDto cDto, Categoria c) {
		c.setNome(cDto.getNome());
	}
}
