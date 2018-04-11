package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Disciplina;
import com.ifpb.dac.interfaces.CursoDao;
import com.ifpb.dac.interfaces.DisciplinaDao;
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
public class ControladorCRUDDisc implements Serializable {
    
    @Inject
    private DisciplinaDao discDao;
    @Inject
    private CursoDao cursoDao;
    private Disciplina disciplina = new Disciplina();
    private boolean editando = false;
    private String valorSelect;
    private List<Disciplina> disciplinas = new ArrayList<>();
    private List<String> cursos = new ArrayList<>();

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

    public List<Disciplina> getDisciplinas() {
        return discDao.listarTodos();
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    public List<String> getCursos() {
        return cursoDao.listarNomeCursos();
    }

    public void setCursos(List<String> cursos) {
        this.cursos = cursos;
    }

    public String getValorSelect() {
        return valorSelect;
    }

    public void setValorSelect(String valorSelect) {
        this.valorSelect = valorSelect;
    }
    
    public String cadastrar(){   
        disciplina.setCurso(cursoDao.retornarPorNome(valorSelect));
        discDao.adicionar(disciplina);
        disciplina = new Disciplina();
        return null;
    }
    
    public String editar(Disciplina d){
        disciplina = d;
        editando = true;
        return null;
    }
    
    public String atualizar(){
        disciplina.setCurso(cursoDao.retornarPorNome(valorSelect));
        discDao.atualizar(disciplina);
        disciplina = new Disciplina();
        editando = false;
        return null;
    }
    
    public String atualizarDiscCurso() {
        discDao.atualizar(disciplina);
        disciplina = new Disciplina();
        editando = false;
        return null;
    }
    
    public String remover(Disciplina d){
        discDao.remover(d);
        return null;
    }
    
}
