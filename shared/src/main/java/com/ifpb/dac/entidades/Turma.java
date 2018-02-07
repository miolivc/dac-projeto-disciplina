package com.ifpb.dac.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

/**
 *
 * @author rodrigobento
 */
@Entity
@SequenceGenerator(name = "minha_seq_turma",
        sequenceName = "seq_turma",
        initialValue = 21394,
        allocationSize = 1)
public class Turma implements Serializable {

    @Id
    @GeneratedValue(generator = "minha_seq_turma", strategy = GenerationType.SEQUENCE)
    @Column(name = "codigo_turma")
    private int codigo_turma;
    
    @Column(name = "identificacao", nullable = false, length = 1)
    private String identificacao;
    
    @Column(name = "disciplina", nullable = false, length = 50)
    private String nome_disciplina;
    
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "codigo_curso")
    private Curso curso;
    
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "codigo_prof")
    private Professor professor;
    
    @OneToMany(mappedBy = "turma", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Aula> aulas;
    
    @OneToMany(mappedBy = "turma", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Material> material;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name = "aluno_turma",
            joinColumns =  @JoinColumn(name = "codigo_turma"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private List<Aluno> alunos;
    
    {
        this.aulas = new ArrayList<>();
        this.alunos = new ArrayList<>();
    }
    
    public Turma(String identificacao, String nome_disciplina, Curso curso, Professor professor) {
        this.identificacao = identificacao;
        this.nome_disciplina = nome_disciplina;
        this.curso = curso;
        this.professor = professor;
    }

    public Turma() {
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }
    
    public boolean add(Aluno a){
        return alunos.add(a);
    }
    
    public boolean rmv(Aluno a){
        return alunos.remove(a);
    }

    public int getCodigo_turma() {
        return codigo_turma;
    }

    public void setCodigo_turma(int codigo_turma) {
        this.codigo_turma = codigo_turma;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getNome_disciplina() {
        return nome_disciplina;
    }

    public void setNome_disciplina(String nome_disciplina) {
        this.nome_disciplina = nome_disciplina;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    public List<Material> getMaterial() {
        return material;
    }

    public void setMaterial(List<Material> material) {
        this.material = material;
    }

}
