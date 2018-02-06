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
    private String valorSelect;
    private boolean cad = false;
    private List<String> tiposUsuario = Arrays.asList("Professor", "Aluno");
    private Professor professor = new Professor();
    private Usuario usuario = new Usuario();
    private HttpSession sessao;

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
        } else {
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
                    return null;
                }
            }
        }
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
        Pedido p = pedidoDao.buscarPorCredenciais(email,
                senha);
        int incrementoPrioridade = p.getPrioridade() + 1;
        p.setPrioridade(incrementoPrioridade);
        pedidoDao.atualizar(p);
        mostrarMensagem("Enviado pedido de acesso para o administrador");
    }

}
