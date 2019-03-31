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

import com.effs.estoque.domain.Produto;
import com.effs.estoque.dto.ProdutoDto;
import com.effs.estoque.services.ProdutoService;

/**
 * @author eduardosatyra
 *
 */

@RestController
@RequestMapping(value = "/api/v1/produtos")
public class ProdutoBuscaResource {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable("id") Integer id) {
		Produto p = this.produtoService.find(id);
		return ResponseEntity.ok().body(p);
	}

	@GetMapping
	public ResponseEntity<List<Produto>> findAll() {
		List<Produto> list = this.produtoService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/search")
	public ResponseEntity<Page<ProdutoDto>> search(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "ids", required = false) List<Integer> ids){
		
		Page<ProdutoDto> pageRequest = this.produtoService.search(nome, ids, page, linesPerPage, orderBy, direction);
		
		return ResponseEntity.ok().body(pageRequest);
	}
}
