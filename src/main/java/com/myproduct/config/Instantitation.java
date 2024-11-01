package com.myproduct.config;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.myproduct.entitys.Pedido;
import com.myproduct.entitys.Produto;
import com.myproduct.repository.PedidoRepository;
import com.myproduct.repository.ProdutoRepository;

@Configuration
public class Instantitation implements CommandLineRunner {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Override
	public void run(String... args) throws Exception {

		Produto produto1 = new Produto();
		produto1.setDescricao("Mouse mecanico super atual");
		produto1.setNome("Mouse Mecanico");
		produto1.setPreco(40.09);
		produto1.setQuantidadeEmEstoque(10L);
		
		Produto produto2 = new Produto();
		produto2.setDescricao("Teclado Mecanico RGB Bluetooth");
		produto2.setNome("Teclado Mecanico");
		produto2.setPreco(75.99);
		produto2.setQuantidadeEmEstoque(3L);
		
		Produto produto3 = new Produto();
		produto3.setDescricao("Alexa inteligente toca musica, som e dispertador");
		produto3.setNome("Alexa");
		produto3.setPreco(250.0);
		produto3.setQuantidadeEmEstoque(0L);
		
		this.produtoRepository.saveAll(Arrays.asList(produto1, produto2, produto3));
		
		
		Pedido pedido1 = new Pedido();
		pedido1.setDataDoPedido(new Date());
		pedido1.getProdutos().addAll(Arrays.asList(produto1, produto2));
		pedido1.setTotal( produto1.getPreco() + produto2.getPreco());
		
		Pedido pedido2 = new Pedido();
		pedido2.setDataDoPedido(new Date());
		pedido2.getProdutos().add(produto1);
		pedido2.setTotal(produto1.getPreco());
		
		this.pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
	}
}