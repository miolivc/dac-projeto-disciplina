package com.ifpb.dac.entidades;

import com.ifpb.dac.enums.Regime;
import com.ifpb.dac.enums.Unidade;
import com.ifpb.dac.enums.Vinculo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Professor implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "codigo_prof")
    private int codigo;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "senha", length = 50, nullable = false)
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

    @OneToMany(mappedBy = "professor", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Aula> aulas;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Turma> turmas;

    {
        this.turmas = new ArrayList<>();
        this.aulas = new ArrayList<>();
    }

    public Professor(String email, String nome, String senha, Regime regime, 
            Unidade unidade, Vinculo vinculo, boolean log) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.regime = regime;
        this.unidade = unidade;
        this.vinculo = vinculo;
        this.logado = log;
    }

    public Professor() {
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

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public boolean add(Turma t){
        return turmas.add(t);
    }
    
    public boolean remover(Turma t){
        return turmas.remove(t);
    }
    
    public boolean addProf(Aula aula){
        return aulas.add(aula);
    }
    
    public boolean remover(Aula aula){
        return aulas.remove(aula);
    }
    
}
