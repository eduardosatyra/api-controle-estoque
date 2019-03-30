package com.effs.estoque.resources.put;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effs.estoque.dto.CategoriaDto;
import com.effs.estoque.services.CategoriaService;

/**
 * @author eduardosatyra
 *
 */

@RestController
@RequestMapping(value = "/api/v1/categorias")
public class CategoriaAlteraResource {

	@Autowired
	private CategoriaService categoriaService;

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable("id") Integer id, @Valid @RequestBody CategoriaDto cDto) {
		cDto.setId(id);
		cDto = this.categoriaService.update(cDto);
		return ResponseEntity.noContent().build();
	}
}
