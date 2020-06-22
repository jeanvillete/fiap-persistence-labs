package br.com.fiap.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PedidosPK implements Serializable {

    @Column(name = "NUMPEDIDO")
    private int codigo;

    @Column(name = "CATEGORIA")
    private String categoria;

    @Override
    public boolean equals(Object other) {
        if (other instanceof PedidosPK) {
            PedidosPK otherPK = (PedidosPK) other;
            if (codigo != otherPK.codigo) {
                return false;
            }
            if (!categoria.equals(otherPK.categoria)) {
                return false;
            }
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return categoria.hashCode() + codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
