package com.myproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproduct.entitys.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
