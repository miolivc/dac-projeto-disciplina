
package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Coordenador;
import com.ifpb.dac.entidades.Curso;
import com.ifpb.dac.entidades.Info;
import com.ifpb.dac.entidades.Pedido;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.enums.TipoUsuario;
import com.ifpb.dac.interfaces.AlunoDao;
import com.ifpb.dac.interfaces.CursoDao;
import com.ifpb.dac.interfaces.PedidoDao;
import com.ifpb.dac.interfaces.ProfessorDao;
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
    private Coordenador coordenador;
    private HttpSession session;
    private Curso curso;
    private Info cursoInfo;
    
    @PostConstruct
    public void init() {
        session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSession(false);
        curso = (Curso) session.getAttribute("curso");
        coordenador = (Coordenador) session.getAttribute("coordenador");
    }
    
    public String atualizarInfoCurso() {
        cursoDao.atualizar(curso);
        return null;
    }
     
    public List<Pedido> pedidos() {
        List<Pedido> pedidos = pedidoDao.listarPedidosPorCurso(curso);
        return pedidos;
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
    
    
    
}
