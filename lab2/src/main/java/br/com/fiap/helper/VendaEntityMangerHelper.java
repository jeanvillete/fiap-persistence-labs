package br.com.fiap.helper;

import br.com.fiap.entity.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

public class VendaEntityMangerHelper {

    private final EntityManager entityManager;

    public VendaEntityMangerHelper(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void salvar(Cliente cliente) throws Exception {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(cliente);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();

            throw e;
        }
    }

    public List<Cliente> listarClientes() {
        return entityManager.createQuery("FROM Cliente", Cliente.class)
                .getResultList();
    }
}
