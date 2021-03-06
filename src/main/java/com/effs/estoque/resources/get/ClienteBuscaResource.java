package com.effs.estoque.resources.get;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.effs.estoque.domain.Cliente;
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
	public ResponseEntity<Cliente> findComplete(@PathVariable("id") Integer id){
		Cliente cli = this.clienteService.findComplete(id);	
		return ResponseEntity.ok().body(cli);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<ClienteDto>> findAll(){
		List<ClienteDto> cDtoList = this.clienteService.findAll();
		return ResponseEntity.ok().body(cDtoList);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping("/page")
	public ResponseEntity<Page<ClienteDto>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {		
		Page<ClienteDto> pageDto = this.clienteService.findPage(page, linesPerPage, orderBy, direction);
		return ResponseEntity.ok().body(pageDto);
	}
}
