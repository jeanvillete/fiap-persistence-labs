package br.com.fiap.test;

import br.com.fiap.entity.Produto;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.stream.IntStream;

public class ClientUtil {

    public static final String URL = "http://localhost:8080/spring-app/estoque/produtos";

    public void getProdutoByIdDemo(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = URL + "/{id}";

        HttpEntity<String> requestEntity = getHttpEntity();

        ResponseEntity<Produto> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Produto.class, id);
        Produto produto = responseEntity.getBody();

        System.out.println(produto);
    }

    public void getAllProdutosDemo() {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<String> httpEntity = getHttpEntity();

        ResponseEntity<Produto[]> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, httpEntity, Produto[].class);
        Produto[] produtos = responseEntity.getBody();

        Arrays.stream(produtos).forEach(System.out::println);
    }

    public void addProdutoDemo(Produto produto) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Produto> httpEntity = getHttpEntity(produto);

        URI uri = restTemplate.postForLocation(URL, httpEntity);

        System.out.println(uri.getPath());
    }

    public void updateProdutoDemo(Produto produto) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Produto> httpEntity = getHttpEntity(produto);
        restTemplate.put(URL, httpEntity);
    }

    public void deleteProdutoDemo(Long id) {
        RestTemplate restTemplate = new RestTemplate();

        String url = URL + "/{id}";

        HttpEntity<String> httpEntity = getHttpEntity();
        restTemplate.exchange(url, HttpMethod.DELETE, httpEntity, Void.class, id);
    }

    public static void main(String[] args) {
        ClientUtil util = new ClientUtil();

        Arrays.asList(
                new Produto("Ameixa"),
                new Produto("Tomate"),
                new Produto("Cereja"),
                new Produto("Coco"),
                new Produto("Cana"),
                new Produto("Atemoia"),
                new Produto("Banana"),
                new Produto("Laranja"),
                new Produto("Pera"),
                new Produto("Maça"),
                new Produto("Melancia"),
                new Produto("Uva"),
                new Produto("Morango"),
                new Produto("Abacaxi"),
                new Produto("Kiwi")
        ).forEach(util::addProdutoDemo);

        IntStream.range(1, 20).forEach(index -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {}
            util.getAllProdutosDemo();
        });

        util.getProdutoByIdDemo(24L);
        util.getProdutoByIdDemo(16L);

        util.deleteProdutoDemo(24L);
        util.deleteProdutoDemo(16L);

        IntStream.range(1, 20).forEach(index -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {}
            util.getAllProdutosDemo();
        });
    }

    private HttpEntity<Produto> getHttpEntity(Produto produto) {
        return new HttpEntity<>(produto, getHttpHeaders());
    }

    private HttpEntity<String> getHttpEntity() {
        return new HttpEntity<>(
                getHttpHeaders()
        );
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
