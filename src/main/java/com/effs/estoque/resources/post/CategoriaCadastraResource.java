package com.effs.estoque.resources.post;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
public class CategoriaCadastraResource {

	@Autowired
	private CategoriaService categoriaService;

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDto cDto, HttpServletRequest req) {
		cDto = this.categoriaService.insert(cDto);
		return ResponseEntity.created(URI.create(req.getContextPath() + "/api/v1/categorias/" + cDto.getId())).build();
	}
}
