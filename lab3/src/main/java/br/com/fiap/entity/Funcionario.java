package br.com.fiap.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "FUNCIONARIO",
        catalog = "dbtarefas",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "CODIGO_FUNCIONARIO")
        }
)
@NamedQuery(name = Funcionario.NAMED_QUERY_FUNCIONARIO_FIND_ALL, query = "FROM Funcionario")
public class Funcionario {

    public static final String NAMED_QUERY_FUNCIONARIO_FIND_ALL = "Funcionario.FindAll";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "CODIGO_FUNCIONARIO", unique = true, nullable = false, length = 10)
    private String matricula;

    @Column(name = "NOME_FUNCIONARIO", unique = true, nullable = false, length = 45)
    private String nome;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "FUNCIONARIO_TAREFA",
            catalog = "dbtarefas",
            joinColumns = {
                    @JoinColumn(name = "FUNCIONARIO_ID", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "TAREFA_ID", nullable = false, updatable = false)
            }
    )
    private Set<Tarefa> tarefas = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Tarefa> getTarefas() {
        return tarefas;
    }

    public void setTarefas(Set<Tarefa> tarefas) {
        this.tarefas = tarefas;
    }
}
