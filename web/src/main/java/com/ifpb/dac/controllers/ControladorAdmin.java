package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.*;
import com.ifpb.dac.enums.TipoUsuario;
import com.ifpb.dac.interfaces.*;
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
    @Inject
    private CoordenadorDao coordenadorDao;

    private Usuario usuario = new Usuario();
    private List<Pedido> pedidos = new ArrayList<>();
    private TipoUsuario filtroPedidos; 

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public TipoUsuario getFiltroPedidos() {
        return filtroPedidos;
    }

    public void setFiltroPedidos(TipoUsuario filtroPedidos) {
        this.filtroPedidos = filtroPedidos;
    }
    
    public List<Pedido> getPedidos() {
//        return pedidoDao.listarTodos();
        return pedidoDao.listarPedidosPorTipoUsuario(TipoUsuario.Coordenador);
    }
    
    public List<Pedido> getPedidos(TipoUsuario tipo) {
        return pedidoDao.listarPedidosPorTipoUsuario(tipo);
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
//        if (p.getTipoUsuario().equals(TipoUsuario.Aluno)) {
//
//            Aluno alunoLib = alunoDao.autentica(p.getEmail(), p.getSenha());
//            if (alunoLib != null) {
//                alunoLib.setLogado(true);
//                alunoDao.atualizar(alunoLib);
//                pedidoDao.remover(p);
//            }
//
//        } else if (p.getTipoUsuario().equals(TipoUsuario.Professor)) {
//
//            Professor prof = professorDao.autentica(p.getEmail(), p.getSenha());
////            Usuario usuLiberado = usuarioDao.autentica(p.getEmail(), p.getSenha(),
////                    p.getTipo());
//            if (prof != null) {
//                prof.setLogado(true);
//                professorDao.atualizar(prof);
//                pedidoDao.remover(p);
//            }
//
//        } else 
            if (p.getTipoUsuario().equals(TipoUsuario.Coordenador)) {

            Coordenador coord = coordenadorDao.autentica(p.getEmail(), p.getSenha());
            if (coord != null) {
                coord.setLogado(true);
                coordenadorDao.atualizar(coord);
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
        }
    }

}
