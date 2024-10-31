package com.myproduct.controller;

import java.util.List;

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

import com.myproduct.entitys.Produto;
import com.myproduct.services.ProdutoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	private final ProdutoService produtoService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto postProduto( @RequestBody Produto produto) {
		return this.produtoService.saveProduto(produto);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Produto> getListProduto() {
		return this.produtoService.listProduto();
	}
	
	@GetMapping("{id}")
	@ResponseStatus(HttpStatus.OK)
	public Produto getProdutoById(@PathVariable Long id) {
		return this.produtoService.findProdutoById(id);
	}
	
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void putProduto(@RequestBody Produto produto) {
		this.produtoService.changeProduto(produto);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteProduto(@PathVariable Long id) {
		this.produtoService.deleteProdutoById(id);
	}

}
