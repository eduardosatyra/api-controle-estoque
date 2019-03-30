package com.effs.estoque.resources.delete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effs.estoque.services.CategoriaService;

/**
 * @author eduardosatyra
 *
 */

@RestController
@RequestMapping(value = "/api/v1/categorias")
public class CategoriaDeleteResource {

	@Autowired
	private CategoriaService categoriaService;

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		this.categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
