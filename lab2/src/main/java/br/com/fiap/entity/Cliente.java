package br.com.fiap.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    @Id
    @Column(name = "ID")
    private int id;

    @Column(name = "EMPRESA", length = 45)
    private String empresa;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
    private Set<Pedido> pedidos = new LinkedHashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
    private Set<Endereco> enderecos = new LinkedHashSet<>();

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", empresa='" + empresa + '\'' +
                ", pedidos=" + pedidos +
                ", enderecos=" + enderecos +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

}
