package com.effs.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.effs.estoque.domain.Cliente;

/**
 * @author eduardosatyra
 *
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
