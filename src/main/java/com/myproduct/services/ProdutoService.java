package com.myproduct.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.myproduct.entitys.Produto;
import com.myproduct.repository.ProdutoRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProdutoService{
	
	@Autowired
	private final ProdutoRepository produtoRepository;
	
	public Produto saveProduto( Produto produto) {
		if(produto.getPreco() < 0) {
			throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,"O valor do numero do preço digitado é negativo e não pode ser aceito. Por favor digite somente numeros positivos");
		}
		return this.produtoRepository.save(produto);
	}
	
	public List<Produto> listProduto() {
		return this.produtoRepository.findAll();
	}
	
	public Produto findProdutoById(Long id) {
		return this.produtoRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado com o id: " + id));
		
	}
	
	public void deleteProdutoById(Long id) {
		this.produtoRepository.findById(id).map(produto -> {
			this.produtoRepository.delete(produto);
			return Void.TYPE;
		}).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	public void changeProduto(Produto produto) {
		this.produtoRepository.findById(produto.getId()).map(produtoAlterado -> {
			produtoAlterado.setDescricao(produto.getDescricao());
			produtoAlterado.setNome(produto.getNome());
			produtoAlterado.setPreco(produto.getPreco());
			produtoAlterado.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque());
			return this.produtoRepository.save(produtoAlterado);
		}).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto com o id: " + produto.getId() + " não encontrado!"));
	}
	

}
