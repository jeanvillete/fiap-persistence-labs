package br.com.fiap.programa;

import br.com.fiap.entity.Funcionario;
import br.com.fiap.entity.Tarefa;
import br.com.fiap.helper.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Teste {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpaFuncionarios");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityManagerHelper dao = new EntityManagerHelper(entityManager);

        incluirFuncionario(dao);
        listarFuncionarios(dao);
        buscarFuncionario(dao);

        entityManager.close();
    }

    private static void incluirFuncionario(EntityManagerHelper dao) {
        Funcionario funcionario = new Funcionario();
        funcionario.setMatricula("2000");
        funcionario.setNome("Alberto Santos");

        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao("Teste Unitário");
        tarefa.setDuracao(100);
        tarefa.getFuncionarios().add(funcionario);

        funcionario.getTarefas().add(tarefa);

        try {
            dao.salvar(funcionario);
            System.out.println("Funcionario persistido");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void listarFuncionarios(EntityManagerHelper dao) {
        dao.listarTodos().forEach(funcionario -> {
            System.out.println("--- dao listarTodos ---");
            System.out.println(funcionario.getMatricula() + ":" + funcionario.getNome());
        });
    }

    private static void buscarFuncionario(EntityManagerHelper dao) {
        String matricula = "2000";

        Funcionario funcionario = dao.buscarFuncionario(matricula);

        System.out.println("--- dao buscarFuncionario por matricula ---");
        System.out.println(funcionario.getMatricula() + ":" + funcionario.getNome());
    }

}
