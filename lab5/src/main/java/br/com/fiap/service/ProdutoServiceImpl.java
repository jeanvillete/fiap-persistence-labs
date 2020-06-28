package br.com.fiap.service;

import br.com.fiap.entity.Produto;
import br.com.fiap.repository.ProdutoRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class ProdutoServiceImpl implements ProdutoService {

    private static final String CACHE_KEY_PRODUTO_CACHE = "produtoCache";
    private static final String CACHE_KEY_ALL_PRODUTOS = "allProdutosCache";

    private final ProdutoRepository produtoRepository;

    ProdutoServiceImpl(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    @Cacheable(value = CACHE_KEY_PRODUTO_CACHE, key = "#id")
    public Produto getProdutoById(Long id) {
        System.out.println("invoked; getProdutoById(" + id + ")");

        return produtoRepository.findById(id)
                .orElse(null);
    }

    @Override
    @Cacheable(value = CACHE_KEY_ALL_PRODUTOS, unless = "#result.size() == 0")
    public List<Produto> getAllProdutos() {
        System.out.println("invoked; getAllProdutos()");

        List<Produto> produtos = new ArrayList<>();
        produtoRepository.findAll()
                .forEach(produtos::add);

        return produtos;
    }

    @Override
    @Caching(
            put = {@CachePut(value = CACHE_KEY_PRODUTO_CACHE, key = "#produto.id")},
            evict = {@CacheEvict(value = CACHE_KEY_ALL_PRODUTOS, allEntries = true)}
    )
    public Produto addProduto(Produto produto) {
        System.out.println("invoked; addProduto(" + produto + ")");

        return produtoRepository.save(produto);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = CACHE_KEY_PRODUTO_CACHE, key = "#produto.id"),
                    @CacheEvict(value = CACHE_KEY_ALL_PRODUTOS, allEntries = true)
            }
    )
    public Produto updateProduto(Produto produto) {
        System.out.println("invoked; updateProduto("+ produto +")");

        return produtoRepository.save(produto);
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = CACHE_KEY_PRODUTO_CACHE, key = "#id"),
                    @CacheEvict(value = CACHE_KEY_ALL_PRODUTOS, allEntries = true)
            }
    )
    public void deleteProduto(Long id) {
        System.out.println("invoked; deleteProduto("  + id + ")");

        Produto produto = getProdutoById(id);
        if (produto == null) {
            throw new IllegalArgumentException("No Produto found for the provided id; " + id);
        }

        produtoRepository.delete(produto);
    }
}
