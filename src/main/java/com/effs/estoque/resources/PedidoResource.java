package com.effs.estoque.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effs.estoque.domain.Pedido;
import com.effs.estoque.services.PedidoService;

/**
 * @author eduardosatyra
 *
 */
@RestController
@RequestMapping(value = "/api/v1/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping("/{id}")
	public ResponseEntity<?> find(@PathVariable("id") Integer id) {
		Pedido p = this.pedidoService.find(id);
		return ResponseEntity.ok().body(p);
	}
}
