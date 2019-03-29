package com.effs.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.effs.estoque.domain.Pagamento;

/**
 * @author eduardosatyra
 *
 */

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer>{

}
