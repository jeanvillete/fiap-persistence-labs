package br.com.fiap.controller;

import br.com.fiap.entity.Produto;
import br.com.fiap.service.ProdutoService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("estoque/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Produto> getProdutoById(@PathVariable("id") Long id) {
        Produto produto = produtoService.getProdutoById(id);

        return ResponseEntity.ok(produto);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getAllProdutos() {
        List<Produto> listaProdutos = produtoService.getAllProdutos();

        return ResponseEntity.ok(listaProdutos);
    }

    @PostMapping
    public ResponseEntity<Void> addProduto(@RequestBody Produto produto, UriComponentsBuilder uriComponentsBuilder) {
        Produto savedProduto = produtoService.addProduto(produto);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                uriComponentsBuilder.path("/produtos/{id}")
                        .buildAndExpand(savedProduto.getId())
                        .toUri()
        );

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Produto> updateProduto(@RequestBody Produto produto) {
        produtoService.updateProduto(produto);

        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduto(@PathVariable("id") Long id) {
        produtoService.deleteProduto(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
