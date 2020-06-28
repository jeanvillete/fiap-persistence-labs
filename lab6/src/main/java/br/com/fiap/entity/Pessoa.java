package br.com.fiap.entity;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Pessoa {

    @Id
    private String id;
    private String nome;
    private List<Endereco> enderecos;

    public Pessoa() {
    }

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public Pessoa(String nome, List<Endereco> enderecos) {
        this.nome = nome;
        this.enderecos = enderecos;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", enderecos=" + enderecos +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
