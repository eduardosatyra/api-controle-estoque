package com.effs.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.effs.estoque.domain.Cidade;

/**
 * @author eduardosatyra
 *
 */
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}
