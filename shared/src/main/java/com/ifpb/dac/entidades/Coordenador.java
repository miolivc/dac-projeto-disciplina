package com.ifpb.dac.entidades;

import com.ifpb.dac.enums.Regime;
import com.ifpb.dac.enums.Unidade;
import com.ifpb.dac.enums.Vinculo;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Coordenador implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "codigo_coordenador")
    private int codigo;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "senha", length = 16, nullable = false)
    private String senha;

    @Column(name = "regime")
    @Enumerated(EnumType.STRING)
    private Regime regime;

    @Column(name = "unidade")
    @Enumerated(EnumType.STRING)
    private Unidade unidade;

    @Column(name = "vinculo")
    @Enumerated(EnumType.STRING)
    private Vinculo vinculo;

    private boolean logado;

    public Coordenador() {
    }

    public Coordenador(String email, String nome, String senha, Regime regime,
                       Unidade unidade, Vinculo vinculo) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.regime = regime;
        this.unidade = unidade;
        this.vinculo = vinculo;
        this.logado = false;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Regime getRegime() {
        return regime;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Vinculo getVinculo() {
        return vinculo;
    }

    public void setVinculo(Vinculo vinculo) {
        this.vinculo = vinculo;
    }

    public boolean isLogado() {
        return logado;
    }

    public void setLogado(boolean logado) {
        this.logado = logado;
    }
    
}
