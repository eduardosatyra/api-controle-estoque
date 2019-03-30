package com.effs.estoque.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.effs.estoque.domain.Pedido;
import com.effs.estoque.repositories.PedidoRepository;
import com.effs.estoque.services.PedidoService;
import com.effs.estoque.services.exception.ObjectNotFoundException;

/**
 * @author eduardosatyra
 *
 */
@Service
public class PedidoServiceImpl implements PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Override
	public Pedido find(Integer id) {
		Optional<Pedido> p = this.pedidoRepository.findById(id);
		return p.orElseThrow(() -> new ObjectNotFoundException(
				"Pedido não encontrado pelo id: " + id + ", Objeto: " + Pedido.class.getName()));
	}

}
