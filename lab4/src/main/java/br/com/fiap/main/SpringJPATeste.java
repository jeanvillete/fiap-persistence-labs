package br.com.fiap.main;

import br.com.fiap.model.Produto;
import br.com.fiap.service.ProdutoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class SpringJPATeste {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = null;
        try {
            ctx = new ClassPathXmlApplicationContext("spring.xml");

            ProdutoService produtoService = ctx.getBean(ProdutoService.class);

            produtoService.add(new Produto("Laranja"));
            produtoService.add(new Produto("Limao"));
            System.out.println(produtoService.findAll());

            produtoService.addAll(
                    Arrays.asList(
                            new Produto("Pera"),
                            new Produto("Morango"),
                            new Produto("Maracuja")
                    )
            );
            System.out.println(produtoService.findAll());

            System.out.println(produtoService.findByName("Maracuja"));
        } finally {
            if (ctx != null) {
                ctx.close();
            }
        }
    }

}
