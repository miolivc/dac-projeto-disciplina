package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Coordenador;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rodrigobento
 */
@RequestScoped
@Named
public class ControladorMenu {

    private String credenciais;
    private HttpSession sessao;
//    private Coordenador coordenador;

    @PostConstruct
    public void init() {
        sessao = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().
                getSession(false);
    }

    public String getCredenciais() {
        return (String) sessao.getAttribute("credenciais");
    }

    public void setCredenciais(String credenciais) {
        this.credenciais = credenciais;
    }
//
//    public Coordenador getCoordenador() {
//        return (Coordenador) sessao.getAttribute("coordenador");
//    }
//
//    public void setCoordenador(Coordenador coordenador) {
//        this.coordenador = coordenador;
//    }
//    
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml";
    }
    
    public void voltar() {
        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();
        try {
            externalContext.redirect("../principal.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
