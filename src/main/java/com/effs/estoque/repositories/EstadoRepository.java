package com.effs.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.effs.estoque.domain.Estado;

/**
 * @author eduardosatyra
 *
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

	Estado findByNome(String nome);
}
