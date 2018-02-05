
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
    @GeneratedValue(generator = "minha_seq_pedido", strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(nullable = false, length = 50)
    private String nome;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 20)
    private String senha;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;
    private int prioridade;

    public Pedido() {
    }

    public Pedido(String nome, String email, String senha, TipoUsuario tipo, int prioridade) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.prioridade = prioridade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuario getTipoUsuario() {
        return tipo;
    }

    public void setTipoUsuario(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }
    
}
