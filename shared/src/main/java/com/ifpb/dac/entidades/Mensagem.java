package com.ifpb.dac.entidades;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Mensagem {

    @Id
    @GeneratedValue
    @Column(name = "id_mensagem")
    private long id;

    private LocalDateTime criadoEm;

    @OneToOne
    private Usuario remetente;

    @OneToOne
    private Usuario destino;

    @Lob
    private String texto;

    {
        this.criadoEm = LocalDateTime.now();
    }

    public Mensagem(Usuario remetente, Usuario destino, String texto) {
        this.remetente = remetente;
        this.destino = destino;
        this.texto = texto;
    }

    public Mensagem() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
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
}
