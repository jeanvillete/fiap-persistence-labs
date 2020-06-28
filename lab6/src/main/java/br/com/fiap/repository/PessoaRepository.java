package br.com.fiap.repository;

import br.com.fiap.entity.Pessoa;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PessoaRepository extends MongoRepository<Pessoa, String> {

    Pessoa findByNome(String nome);

    Pessoa findByNomeLike(String nome);

}
