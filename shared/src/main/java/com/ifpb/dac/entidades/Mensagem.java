/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.dac.entidades;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 *
 * @author recursive
 */
@Entity
public class Mensagem implements Serializable {
    
    @Id
    private int id;
    @ManyToOne
    private Usuario remetente;
    @ManyToOne
    private Usuario destino;
    @Lob
    private String texto;

    private LocalDateTime criadoEm;

    {
        this.criadoEm = LocalDateTime.now();
    }
    
    public Mensagem() {
    }

    public Mensagem(Usuario remetente, Usuario destino, String texto) {
        this.remetente = remetente;
        this.destino = destino;
        this.texto = texto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Usuario getDestino() {
        return destino;
    }

    public void setDestino(Usuario destino) {
        this.destino = destino;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    @Override
    public String toString() {
        return "Mensagem{" + "id=" + id + ", remetente=" + remetente + ", destino=" 
                + destino + ", texto=" + texto + ", criadoEm=" + criadoEm + '}';
    }
    
    
}
