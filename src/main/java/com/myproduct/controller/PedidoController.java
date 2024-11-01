package com.myproduct.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myproduct.entitys.Pedido;
import com.myproduct.entitys.dto.PedidoDTO;
import com.myproduct.services.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	
	@Autowired
	private PedidoService pedidoService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pedido postPedido( @RequestBody PedidoDTO pedidoDTO) {
		return this.pedidoService.salvarPedido(pedidoDTO);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Pedido> getPedidos(){
		return this.pedidoService.listPedidos();
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public Pedido getPedidoById(@PathVariable Long id) {
		return this.pedidoService.pedidoById(id);
	}
	
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletePedido(@PathVariable Long id) {
		this.pedidoService.deletePedido(id);
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void putPedido(@RequestBody PedidoDTO pedidoDTO) {
		this.pedidoService.changePedido(pedidoDTO);
	}
}
