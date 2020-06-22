package br.com.fiap.helper;

import br.com.fiap.entity.Funcionario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EntityManagerHelper {

    private final EntityManager em;

    public EntityManagerHelper(EntityManager em) {
        this.em = em;
    }

    public void salvar(Funcionario funcionario) {
        em.getTransaction().begin();
        em.persist(funcionario);
        em.getTransaction().commit();
    }

    public List<Funcionario> listarFuncionarios() {
        return em.createQuery("SELECT f FROM Funcionario f", Funcionario.class)
                .getResultList();
    }

    public Funcionario buscarFuncionario(String numMatricula) {
        TypedQuery<Funcionario> query = em.createQuery("SELECT f FROM Funcionario f WHERE matricula = :matricula", Funcionario.class);
        query.setParameter("matricula", numMatricula);

        return query.getSingleResult();
    }

    public List<Funcionario> listarTodos() {
        return em.createNamedQuery(Funcionario.NAMED_QUERY_FUNCIONARIO_FIND_ALL, Funcionario.class)
                .getResultList();
    }
}
