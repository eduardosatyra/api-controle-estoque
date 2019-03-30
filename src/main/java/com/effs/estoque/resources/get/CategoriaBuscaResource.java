package com.effs.estoque.resources.get;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.effs.estoque.dto.CategoriaDto;
import com.effs.estoque.services.CategoriaService;

/**
 * @author eduardosatyra
 *
 */

@RestController
@RequestMapping(value = "/api/v1/categorias")
public class CategoriaBuscaResource {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaDto> find(@PathVariable("id") Integer id) {
		CategoriaDto cDto = this.categoriaService.find(id);
		return ResponseEntity.ok().body(cDto);
	}

	@GetMapping
	public ResponseEntity<List<CategoriaDto>> findAll() {
		List<CategoriaDto> cDtoList = this.categoriaService.findAll();
		return ResponseEntity.ok().body(cDtoList);
	}
	
	@GetMapping("/page")
	public ResponseEntity<Page<CategoriaDto>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {		
		Page<CategoriaDto> pageDto = this.categoriaService.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(pageDto);
	}
}
