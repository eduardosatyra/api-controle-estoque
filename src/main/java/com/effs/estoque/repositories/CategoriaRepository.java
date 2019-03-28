package com.effs.estoque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.effs.estoque.domain.Categoria;

/**
 * @author eduardosatyra
 *
 */

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
