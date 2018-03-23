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
public class ControladorUsuario implements Serializable {

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
    private HttpSession sessao;
    private TipoUsuario tipo;
    private String valorSelect;
    private boolean cad = false;
    private Usuario usuario = new Usuario();
    private Coordenador coordenador = new Coordenador();

    public List<TipoUsuario> todosOsTiposUsuario() {
        return Arrays.asList(TipoUsuario.Coordenador, TipoUsuario.Aluno, TipoUsuario.Professor);
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
        if (tipo.equals(TipoUsuario.Professor)) {
            return loginProfessor();
        } else if (tipo.equals(TipoUsuario.Coordenador)) {
            return loginCoordenador();
        }
        return loginAluno();
    }

    public String entrarComoPublico() {
        iniciarSessao();
        sessao.setAttribute("credenciais", "publico");
        return "principal.xhtml";
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

    public void mostrarMensagem(String msg) {
        FacesMessage message = new FacesMessage(msg);
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage("Acesso", message);
    }

    private String loginCoordenador() {
        Coordenador autenticado = coordenadorDao.autentica(usuario.getEmail(), usuario.getSenha());
        usuario = new Usuario();
        if (autenticado == null) {
            mostrarMensagem("Email e senha invalidos!");
            return null;
        }
        if (autenticado.isLogado()) {
            iniciarSessao();
            sessao.setAttribute("coordenador", autenticado);
            sessao.setAttribute("credenciais", "coord");
            return "principal.xhtml";
        }
        atualizarPedido(autenticado.getEmail(), autenticado.getSenha());
        return null;
    }

    public String loginProfessor() {
        Professor autenticado = professorDao.autentica(usuario.getEmail(), usuario.getSenha());
        usuario = new Usuario();
        if (autenticado == null) {
            mostrarMensagem("Email e senha invalidos!");
            return null;
        }
        if (autenticado.isLogado()) {
            iniciarSessao();
            sessao.setAttribute("usuario", autenticado);
            sessao.setAttribute("credenciais", "prof");
            return "principal.xhtml";
        }
        atualizarPedido(autenticado.getEmail(), autenticado.getSenha());
        return null;
    }

    private String loginAluno() {
        Aluno autenticado = alunoDao.autentica(usuario.getEmail(), usuario.getSenha());
        usuario = new Usuario();
        if (autenticado == null) {
            mostrarMensagem("Email e senha invalidos!");
            return null;
        }
        if (autenticado.isLogado()) {
            iniciarSessao();
            sessao.setAttribute("aluno", autenticado);
            sessao.setAttribute("credenciais", "aluno");
            return "principal.xhtml";
        }
        atualizarPedido(autenticado.getEmail(), autenticado.getSenha());
        return null;
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

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

}
