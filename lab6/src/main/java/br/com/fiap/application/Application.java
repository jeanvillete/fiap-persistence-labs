package br.com.fiap.application;

import br.com.fiap.entity.Endereco;
import br.com.fiap.entity.Pessoa;
import br.com.fiap.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {PessoaRepository.class})
public class Application implements CommandLineRunner {

    @Autowired
    public PessoaRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        repository.save(new Pessoa("Jean"));

        List<Endereco> enderecos = Arrays.asList(
                new Endereco("Avenida Morumbi", "SP"),
                new Endereco("Avenida Paulista", "SP")
        );

        repository.save(new Pessoa("Paulo", enderecos));
        repository.save(new Pessoa("Julia", enderecos));

        repository.findAll().forEach(System.out::println);
        System.out.println();

        // find by nome Julia
        System.out.println("find by nome; Julia");
        System.out.println("---------------------------");
        System.out.println(repository.findByNome("Julia"));
        System.out.println();

        // find by nome usando like
        System.out.println("find by nome like; Pa");
        System.out.println("---------------------------");
        System.out.println(repository.findByNomeLike("Pa"));
        System.out.println();
    }

}
