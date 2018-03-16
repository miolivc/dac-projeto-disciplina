package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.*;
import com.ifpb.dac.enums.TipoUsuario;
import com.ifpb.dac.interfaces.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rodrigobento
 */
@Named
@RequestScoped
public class  ControladorUsuario implements Serializable {

    @Inject
    private UsuarioDao usuarioDao;
    @Inject
    private PedidoDao pedidoDao;
    @Inject
    private AlunoDao alunoDao;
    @Inject
    private ProfessorDao professorDao;
    @Inject
    private CoordenadorDao coordenadorDao;

    private String valorSelect;
    private boolean cad = false;
    private List<String> tiposUsuario = Arrays.asList("Professor", "Aluno", "Coordenador");
    private Professor professor = new Professor();
    private Usuario usuario = new Usuario();
    private HttpSession sessao;

    private Coordenador coordenador;

    public List<String> getTiposUsuario() {
        return tiposUsuario;
    }

    public void setTiposUsuario(List<String> palavras) {
        this.tiposUsuario = palavras;
    }

    public String getValorSelect() {
        return valorSelect;
    }

    public void setValorSelect(String valorSelect) {
        this.valorSelect = valorSelect;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public boolean isCad() {
        return cad;
    }

    public void setCad(boolean cad) {
        this.cad = cad;
    }
    
    public String navegarCadastro() {
        return "cadastros/cadastro.xhtml";
    }

    public String navegarCadastroCoordenador() {
        return "cadastros/cadastroCoordenador.xhtml";
    }
    
    public String navegarCadastroProfessor() {
        return "cadastros/cadastroProfessor.xhtml";
    }
    
    public Coordenador getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(Coordenador coordenador) {
        this.coordenador = coordenador;
    }

    public String realizarLogin() {
        TipoUsuario tipo = Enum.valueOf(TipoUsuario.class, valorSelect);
        // Caso seja login de professor
        if (tipo.equals(TipoUsuario.Professor)) {
            Professor autentica = professorDao.autentica(usuario.getEmail(), usuario.getSenha());
            usuario = new Usuario();
            if (autentica != null) {
                if (autentica.isLogado()) {
                    iniciarSessao();
                    sessao.setAttribute("usuario", autentica);
                    sessao.setAttribute("credenciais", "prof");
                    return "principal.xhtml";
                } else {
                    atualizarPedido(autentica.getEmail(), autentica.getSenha());
                    return null;
                }
            } else {
                mostrarMensagem("Email e senha invalidos!");
                return null;
            }
        } else if(tipo.equals(TipoUsuario.Coordenador)) {
            Coordenador autenticado = coordenadorDao.autentica(usuario.getEmail(),
                    usuario.getSenha());
            usuario = new Usuario();
            if (autenticado == null) {
                mostrarMensagem("Email e senha invalidos!");
                return null;
            } else {
                if (autenticado.isLogado()) {
                    iniciarSessao();
                    sessao.setAttribute("coordenador", autenticado);
                    sessao.setAttribute("credenciais", "coord");
                    return "principal.xhtml";
                } else {
                    atualizarPedido(autenticado.getEmail(), autenticado.getSenha());
                    return null;
                }
            }
        } else if(tipo.equals(TipoUsuario.Aluno)) {
            Aluno autenticado = alunoDao.autentica(usuario.getEmail(),
                    usuario.getSenha());
            usuario = new Usuario();
            if (autenticado == null) {
                mostrarMensagem("Email e senha invalidos!");
                return null;
            } else {
                if (autenticado.isLogado()) {
                    iniciarSessao();
                    sessao.setAttribute("aluno", autenticado);
                    sessao.setAttribute("credenciais", "aluno");
                    return "principal.xhtml";
                } else {
                    atualizarPedido(autenticado.getEmail(), autenticado.getSenha());
                }
            }
        }
        return null;
    }

    public String entrarComoPublico() {
        iniciarSessao();
        sessao.setAttribute("credenciais", "publico");
        return "principal.xhtml";
    }

    public void mostrarMensagem(String msg) {
        FacesMessage message = new FacesMessage(msg);
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage("Acesso", message);
    }

    private void iniciarSessao() {
        sessao = (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().getSession(true);
    }

    private void atualizarPedido(String email, String senha) {
        Pedido p = pedidoDao.buscarPorCredenciais(email, senha);
        int incrementoPrioridade = p.getPrioridade() + 1;
        p.setPrioridade(incrementoPrioridade);
        pedidoDao.atualizar(p);
        mostrarMensagem("Enviado pedido de acesso para o administrador");
    }

}
