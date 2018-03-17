package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Curso;
import com.ifpb.dac.entidades.Info;
import com.ifpb.dac.interfaces.AlunoDao;
import com.ifpb.dac.interfaces.CursoDao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author rodrigobento
 */
@Named
@SessionScoped
public class ControladorCRUDCurso implements Serializable {

    @Inject
    private CursoDao cursoDao;
    @Inject
    private AlunoDao alunoDao;
    private Curso curso = new Curso();
    private boolean editando = false;
    private List<Curso> cursos = new ArrayList<>();
    private String descricao;
    private String abreviacao;

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

    public List<Curso> getCursos() {
        return cursoDao.listarTodos();
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public void setAbreviacao(String abreviacao) {
        this.abreviacao = abreviacao;
    }

    public String cadastrar() {
        Info info = new Info(abreviacao, descricao);
        curso.setInfo(info);
        cursoDao.adicionar(curso);
        limparCampos();
        return null;
    }

    public String editar(Curso c) {
        descricao = c.getInfo().getDescricao();
        abreviacao = c.getInfo().getAbreviacao();
        curso = c;
        editando = true;
        return null;
    }

    public String atualizar() {
        Info info = new Info(abreviacao, descricao);
        curso.setInfo(info);
        cursoDao.atualizar(curso);
        limparCampos();
        editando = false;
        return null;
    }

    public String remover(Curso c) {
        cursoDao.remover(c);
        return null;
    }

    private void limparCampos() {
        curso = new Curso();
        abreviacao = null;
        descricao = null;
    }
    
}
