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

@Entity
@SequenceGenerator(name = "minha_seq_usu",
        sequenceName = "seq_usu",
        initialValue = 106,
        allocationSize = 1)
public class Usuario implements Serializable {
    
    @Id
    @GeneratedValue(generator = "minha_seq_usu", strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(nullable = false, length = 50)
    private String nome;
    @Column(nullable = false, length = 50)
    private String email;
    @Column(nullable = false, length = 20)
    private String senha;
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;
    private boolean logado;

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha,
            TipoUsuario tipo, boolean logado) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.logado = logado;
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

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }   

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nome=" + nome + ", email=" + email + ", senha="
                + senha + ", Tipo=" + tipo + ", logado=" + logado + '}';
    }
    
    
}
