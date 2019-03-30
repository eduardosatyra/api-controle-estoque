package com.effs.estoque.resources.get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effs.estoque.dto.ClienteDto;
import com.effs.estoque.services.ClienteService;

/**
 * @author eduardosatyra
 *
 */
@RestController
@RequestMapping(value = "/api/v1/clientes")
public class ClienteBuscaResource {

	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDto> find(@PathVariable("id") Integer id){
		ClienteDto cli = this.clienteService.find(id);	
		return ResponseEntity.ok().body(cli);
	}
}
