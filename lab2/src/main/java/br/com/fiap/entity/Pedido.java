package br.com.fiap.entity;

import br.com.fiap.pk.PedidosPK;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @EmbeddedId
    private PedidosPK pedidosPK;

    @Column(name = "DATAPEDIDO")
    private LocalDateTime dataPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDCLIENTE")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pedido")
    private Set<Item> itens = new LinkedHashSet<>();

    @Override
    public String toString() {
        return "Pedido{" +
                "dataPedido=" + dataPedido +
                ", itens=" + itens +
                '}';
    }

    public PedidosPK getPedidosPK() {
        return pedidosPK;
    }

    public void setPedidosPK(PedidosPK pedidosPK) {
        this.pedidosPK = pedidosPK;
    }

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<Item> getItens() {
        return itens;
    }

    public void setItens(Set<Item> itens) {
        this.itens = itens;
    }
}
