
package com.ifpb.dac.entidades;

import com.ifpb.dac.enums.TipoUsuario;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author rodrigobento
 */
@Entity
@SequenceGenerator(name = "minha_seq_pedido",
        sequenceName = "seq_pedido",
        initialValue = 106,
        allocationSize = 1)
public class Pedido implements Serializable {
    
    @Id
    @GeneratedValue(generator = "minha_seq_pedido", 
            strategy = GenerationType.SEQUENCE)
    private int id;
    @ManyToOne
    private Usuario usuario;
    private int prioridade;

    public Pedido() {
    }

    public Pedido(Usuario usuario, int prioridade) {
        this.usuario = usuario;
        this.prioridade = prioridade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
    
}
