package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Curso;
import com.ifpb.dac.entidades.Disciplina;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.entidades.Turma;
import com.ifpb.dac.interfaces.CursoDao;
import com.ifpb.dac.interfaces.DisciplinaDao;
import com.ifpb.dac.interfaces.ProfessorDao;
import com.ifpb.dac.interfaces.TurmaDao;
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
public class ControladorCRUDTurma implements Serializable {
    
    @Inject
    private TurmaDao turmaDao;
    @Inject
    private CursoDao cursoDao;
    @Inject
    private ProfessorDao profDao;
    @Inject
    private DisciplinaDao discDao;
    private Turma turma = new Turma();
    private List<Turma> turmas = new ArrayList<>();
    private List<String> cursos = new ArrayList<>();
    private List<String> professores = new ArrayList<>();
    private List<String> discs = new ArrayList<>();
    private boolean editando = false;
    private String valorCurso;
    private String valorProf;
    private String valorDisc;

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public List<Turma> getTurmas() {
        return turmaDao.listarTodos();
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public List<String> getCursos() {
        return cursoDao.listarNomeCursos();
    }

    public void setCursos(List<String> cursos) {
        this.cursos = cursos;
    }

    public List<String> getProfessores() {
        return profDao.listarNomeProfessores();
    }

    public void setProfessores(List<String> professores) {
        this.professores = professores;
    }

    public List<String> getDiscs() {
        return discDao.listarNomeDisciplinas();
    }

    public void setDiscs(List<String> discs) {
        this.discs = discs;
    }

    public boolean isEditando() {
        return editando;
    }

    public void setEditando(boolean editando) {
        this.editando = editando;
    }

    public String getValorCurso() {
        return valorCurso;
    }

    public void setValorCurso(String valorCurso) {
        this.valorCurso = valorCurso;
    }

    public String getValorProf() {
        return valorProf;
    }

    public void setValorProf(String valorProf) {
        this.valorProf = valorProf;
    }

    public String getValorDisc() {
        return valorDisc;
    }

    public void setValorDisc(String valorDisc) {
        this.valorDisc = valorDisc;
    }
    
    public String adicionar(){
        Curso curso = cursoDao.retornarPorNome(valorCurso);
        turma.setCurso(curso);
        Professor professor = profDao.buscarPorNome(valorProf);
        turma.setProfessor(professor);
        turma.setNome_disciplina(valorDisc);
        turmaDao.adicionar(turma);
        turma = new Turma();
        return null;
    }
    
    public String editar(Turma t){
        editando = true;
        turma = t;
        List<String> discs1 = getDiscs();
        List<String> professores1 = getProfessores();
        List<String> cursos1 = getCursos();
        discs1.set(0, t.getNome_disciplina());
        professores1.set(0, t.getProfessor().getNome());
        cursos1.set(0, t.getCurso().getInfo().getDescricao());
        setDiscs(discs1);
        setProfessores(professores1);
        setCursos(cursos1);
        return null;
    }
    
    public String atualizar(){
        turma.setNome_disciplina(valorDisc);
        turma.setCurso(cursoDao.retornarPorNome(valorCurso));
        turma.setProfessor(profDao.buscarPorNome(valorProf));
        turmaDao.atualizar(turma);
        turma = new Turma();
        editando = false;
        return null;
    }
    
    /**
     * Refere-se a atualização de uma turma que não pode trocar de curso,
     * já que o usuário (Coordenador), não tem esse tipo de permissão
     * @return link da própria página que chamou o método
     */
    public String atualizarEspecifico(){
        turma.setNome_disciplina(valorDisc);
        turma.setProfessor(profDao.buscarPorNome(valorProf));
        turmaDao.atualizar(turma);
        turma = new Turma();
        editando = false;
        return null;
    }
    
    public String remover(Turma t){
        turmaDao.remover(t);
        return null;
    } 
    
}
