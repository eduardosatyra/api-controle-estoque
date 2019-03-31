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

import com.effs.estoque.dto.ClienteNewDto;
import com.effs.estoque.services.ClienteService;

/**
 * @author eduardosatyra
 *
 */
@RestController
@RequestMapping(value = "/api/v1/clientes")
public class ClienteCadastraResource {

	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDto cDto, HttpServletRequest req) {
		cDto.setId(null);
		ClienteNewDto cli = this.clienteService.insert(cDto);
		return ResponseEntity.created(URI.create(req.getContextPath() + "/api/v1/categorias/" + cli.getId())).build();
	}
}
