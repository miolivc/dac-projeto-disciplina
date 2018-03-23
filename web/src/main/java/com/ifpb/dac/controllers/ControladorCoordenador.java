
package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Coordenador;
import com.ifpb.dac.entidades.Curso;
import com.ifpb.dac.entidades.Disciplina;
import com.ifpb.dac.entidades.Pedido;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.entidades.Turma;
import com.ifpb.dac.enums.TipoUsuario;
import com.ifpb.dac.interfaces.AlunoDao;
import com.ifpb.dac.interfaces.CursoDao;
import com.ifpb.dac.interfaces.DisciplinaDao;
import com.ifpb.dac.interfaces.PedidoDao;
import com.ifpb.dac.interfaces.ProfessorDao;
import com.ifpb.dac.interfaces.TurmaDao;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class ControladorCoordenador {

    @Inject
    private PedidoDao pedidoDao;
    @Inject
    private AlunoDao alunoDao;
    @Inject
    private ProfessorDao professorDao;
    @Inject
    private CursoDao cursoDao;
    @Inject
    private TurmaDao turmaDao;
    @Inject
    private DisciplinaDao disciplinas;
    
    private Disciplina disciplina = new Disciplina();
    private Coordenador coordenador;
    private HttpSession session;
    private Curso curso;
    
    @PostConstruct
    public void init() {
        session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSession(false);
        coordenador = (Coordenador) session.getAttribute("coordenador");
        curso = coordenador.getCurso();
        
    }
    
    public String atualizarInfoCurso() {
        cursoDao.atualizar(curso);
        return "principal.xhtml";
    }
    
    public String atualizardisciplina() {
        disciplinas.atualizar(disciplina);
        return null;
    }

    public String liberarAcesso(Pedido p) {
        if (p.getTipoUsuario().equals(TipoUsuario.Aluno)) {

            Aluno alunoLib = alunoDao.autentica(p.getEmail(), p.getSenha());
            if (alunoLib != null) {
                alunoLib.setLogado(true);
                alunoDao.atualizar(alunoLib);
                pedidoDao.remover(p);
            }

        } else if (p.getTipoUsuario().equals(TipoUsuario.Professor)) {

            Professor prof = professorDao.autentica(p.getEmail(), p.getSenha());
            if (prof != null) {
                prof.setLogado(true);
                professorDao.atualizar(prof);
                pedidoDao.remover(p);
            }
        } 
        return null;
    }

    public List<Disciplina> disciplinas() {
        return Collections.unmodifiableList(disciplinas.listarTodos(curso));
    }
    
    public List<Pedido> pedidos() {
        return pedidoDao.listarPedidosPorCurso(curso);
    }
    
    public List<Professor> professores() {
        return professorDao.listarTodos(coordenador.getUnidade().name());
    }
    
    public List<Turma> turmas() {
        return turmaDao.listarTodos(curso);
    }
    
    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Coordenador getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
}
