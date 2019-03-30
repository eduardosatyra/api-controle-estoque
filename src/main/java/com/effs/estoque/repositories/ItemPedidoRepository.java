package com.effs.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.effs.estoque.domain.ItemPedido;

/**
 * @author eduardosatyra
 *
 */

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
