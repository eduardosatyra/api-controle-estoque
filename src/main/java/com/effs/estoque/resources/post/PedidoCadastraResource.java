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

import com.effs.estoque.domain.Pedido;
import com.effs.estoque.dto.PedidoDto;
import com.effs.estoque.services.PedidoService;

/**
 * @author eduardosatyra
 *
 */

@RestController
@RequestMapping(value = "/api/v1/pedidos")
public class PedidoCadastraResource {

	@Autowired
	private PedidoService pedidoService;

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody PedidoDto pDto, HttpServletRequest req) {
		Pedido p = this.pedidoService.insert(pDto);
		return ResponseEntity.created(URI.create(req.getContextPath() + "/api/v1/pedidos/" + p.getId())).build();
	}
}
