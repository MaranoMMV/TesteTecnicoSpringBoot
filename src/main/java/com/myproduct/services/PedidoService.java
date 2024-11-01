package com.myproduct.services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.myproduct.entitys.Pedido;
import com.myproduct.entitys.Produto;
import com.myproduct.entitys.dto.PedidoDTO;
import com.myproduct.repository.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ProdutoService produtoService;

	@Transactional
	public Pedido salvarPedido(PedidoDTO pedidoDTO) {
		System.out.println(pedidoDTO.toString());
		Pedido pedido = new Pedido();
		for (Long idProduto : pedidoDTO.getIdProdutos()) {
			Produto produto = this.produtoService.findProdutoById(idProduto);
			if (produto.getQuantidadeEmEstoque() >= 1) {
				pedido.getProdutos().add(produto);

				produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - 1);
				this.produtoService.changeProduto(produto);
			} else {

				throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
						"ocorreu um erro com o id: " + idProduto + " Verifique se o id inserido realmente existe! ou o item está com estoque");
			}
		}

		pedido.setDataDoPedido(new Date());
		return this.pedidoRepository.save(pedido);
	}

	public List<Pedido> listPedidos() {
		return this.pedidoRepository.findAll();
	}
	
	public Pedido pedidoById(Long id) {
		return this.pedidoRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado com o id: " + id));
	}

	public void deletePedido(Long id) {
		this.pedidoRepository.findById(id).map(pedido -> {
			this.pedidoRepository.delete(pedido);
			return Void.TYPE;
		}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@Transactional
	public void changePedido(PedidoDTO pedidoDTO) {
		this.pedidoRepository.findById(pedidoDTO.getId()).map(pedidoAlterado -> {
			pedidoAlterado.getProdutos().clear();
			for (Long idProduto : pedidoDTO.getIdProdutos()) {
				Produto produto = this.produtoService.findProdutoById(idProduto);
				if (produto.getQuantidadeEmEstoque() >= 1) {
					pedidoAlterado.getProdutos().add(produto);
					produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - 1);
					this.produtoService.changeProduto(produto);
				} else {
					throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
							"ocorreu um erro com o id: " + idProduto + " Verifique se o id inserido realmente existe!");
				}
			}
			return pedidoAlterado;
		}).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido com o id: " + pedidoDTO.getId() + " Não foi encontrado"));

	}
}
