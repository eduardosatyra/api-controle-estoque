package com.effs.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.effs.estoque.domain.Endereco;

/**
 * @author eduardosatyra
 *
 */
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{

}
