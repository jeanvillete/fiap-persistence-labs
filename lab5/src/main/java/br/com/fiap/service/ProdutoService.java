package br.com.fiap.service;

import br.com.fiap.entity.Produto;

import java.util.List;

public interface ProdutoService {

    List<Produto> getAllProdutos();
    Produto getProdutoById(Long id);
    Produto addProduto(Produto produto);
    Produto updateProduto(Produto produto);
    void deleteProduto(Long id);

}
