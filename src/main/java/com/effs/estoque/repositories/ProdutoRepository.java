package com.effs.estoque.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.effs.estoque.domain.Categoria;
import com.effs.estoque.domain.Produto;

/**
 * @author eduardosatyra
 *
 */

@Repository
@Transactional(readOnly = true)
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	//@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<Produto> findDistinctByNomeIgnoreCaseContainingAndCategoriasIn(String nome, List<Categoria> ids, Pageable pageRequest);
	
}
