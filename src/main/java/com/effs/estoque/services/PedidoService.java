package com.effs.estoque.services;

import com.effs.estoque.domain.Pedido;
import com.effs.estoque.dto.PedidoDto;

/**
 * @author eduardosatyra
 *
 */
public interface PedidoService {

	Pedido find(Integer id);

	Pedido insert(PedidoDto pDto);
}
