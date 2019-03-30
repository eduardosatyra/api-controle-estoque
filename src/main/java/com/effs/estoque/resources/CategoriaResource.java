package com.effs.estoque.resources;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDto> find(@PathVariable("id") Integer id) {
		CategoriaDto cDto = this.categoriaService.find(id);
		return ResponseEntity.ok().body(cDto);
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDto cDto, HttpServletRequest req) {
		cDto = this.categoriaService.insert(cDto);
		return ResponseEntity.created(URI.create(req.getContextPath() + "/api/v1/categorias/" + cDto.getId())).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable("id") Integer id, @Valid @RequestBody CategoriaDto cDto,
			HttpServletRequest req) {
		cDto.setId(id);
		cDto = this.categoriaService.update(cDto);
		return ResponseEntity.noContent().build();
	}
}
