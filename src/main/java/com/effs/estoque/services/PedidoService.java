package com.effs.estoque.services;

import org.springframework.data.domain.Page;

import com.effs.estoque.domain.Pedido;
import com.effs.estoque.dto.PedidoDto;

/**
 * @author eduardosatyra
 *
 */
public interface PedidoService {

	Pedido find(Integer id);

	Pedido insert(PedidoDto pDto);
	
	Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction);
}
