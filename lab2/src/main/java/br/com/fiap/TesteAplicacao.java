package br.com.fiap;

import br.com.fiap.entity.Cliente;
import br.com.fiap.entity.Endereco;
import br.com.fiap.entity.Item;
import br.com.fiap.entity.Pedido;
import br.com.fiap.helper.VendaEntityMangerHelper;
import br.com.fiap.pk.PedidosPK;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class TesteAplicacao {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpaVendas");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        VendaEntityMangerHelper dao = new VendaEntityMangerHelper(entityManager);

        try {
            salvaCliente(dao);

            mostraClientes(dao);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mostraClientes(VendaEntityMangerHelper dao) {
        List<Cliente> clientes = dao.listarClientes();
        System.out.println("--- cliente cadastrado ---");
        clientes.forEach(System.out::println);
    }

    private static void salvaCliente(VendaEntityMangerHelper dao) throws Exception {
        Cliente cliente = new Cliente();
        cliente.setId(10);
        cliente.setEmpresa("Fiap");

        Endereco endereco = new Endereco();
        endereco.setRua("Lins de Vasconcelos");
        endereco.setCidade("São Paulo");
        endereco.setCep("01538-001");
        endereco.setCliente(cliente);

        PedidosPK pedidosPK = new PedidosPK();
        pedidosPK.setCodigo(100);
        pedidosPK.setCategoria("Livros");

        Pedido pedido = new Pedido();
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setPedidosPK(pedidosPK);
        pedido.setCliente(cliente);

        Item item1 = new Item();
        item1.setQuantidade(2);

        Item item2 = new Item();
        item2.setQuantidade(3);

        pedido.getItens().add(item1);
        pedido.getItens().add(item2);

        cliente.getEnderecos().add(endereco);
        cliente.getPedidos().add(pedido);

        dao.salvar(cliente);
    }

}
