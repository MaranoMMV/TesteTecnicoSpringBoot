package com.myproduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myproduct.entitys.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
