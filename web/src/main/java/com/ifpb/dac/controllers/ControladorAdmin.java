package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Pedido;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.entidades.Usuario;
import com.ifpb.dac.enums.TipoUsuario;
import com.ifpb.dac.interfaces.AlunoDao;
import com.ifpb.dac.interfaces.PedidoDao;
import com.ifpb.dac.interfaces.ProfessorDao;
import com.ifpb.dac.interfaces.UsuarioDao;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author rodrigobento
 */
@Named
@RequestScoped
public class ControladorAdmin implements Serializable {

    @Inject
    private UsuarioDao usuarioDao;
    @Inject
    private ProfessorDao professorDao;
    @Inject
    private AlunoDao alunoDao;
    @Inject
    private PedidoDao pedidoDao;
    private Usuario usuario = new Usuario();
    private Aluno aluno = new Aluno();
    private List<Pedido> pedidos = new ArrayList<>();

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Pedido> getPedidos() {
        return pedidoDao.listarTodos();
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public String realizarLogin() {
        Usuario admin = usuarioDao.autentica(usuario.getEmail(),
                usuario.getSenha(), TipoUsuario.Administrador);
        if (admin != null) {
            return "menu.xhtml";
        } else {
            usuario = new Usuario();
            return null;
        }
    }

    public String liberarAcesso(Pedido p) {
        if (p.getTipoUsuario().equals(TipoUsuario.Aluno)) {
            Aluno alunoLib = alunoDao.autentica(p.getEmail(), p.getSenha());
            if (alunoLib != null) {
                alunoLib.setLogado(true);
                alunoDao.atualizar(alunoLib);
                pedidoDao.remover(p);
            }
        } else {
            Professor prof = professorDao.autentica(p.getEmail(), p.getSenha());
//            Usuario usuLiberado = usuarioDao.autentica(p.getEmail(), p.getSenha(),
//                    p.getTipo());
            if (prof != null) {
                prof.setLogado(true);
                professorDao.atualizar(prof);
                pedidoDao.remover(p);
            }
        }        
        return null;
    }

    public void logout() {
        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();
        externalContext.invalidateSession();
        try {
            externalContext.redirect("../index.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
