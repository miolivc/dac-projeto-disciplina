package com.ifpb.dac.entidades;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rodrigobento
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class HorariosDTO implements Serializable {
    
    private String dia;
    private Calendar inicio;
    private Calendar fim;
    private String descricao;
    private String nome;
    private String valorInicio;
    private String valorFim;
    private String descricaoSala;
    private String descricaoLab;
    private String local;

    public HorariosDTO() {
    }
    
    public HorariosDTO(String dia, String descricao, Calendar inicio, 
            Calendar fim, String nome, String descLab, String descSala) {
        this.dia = dia;
        this.descricao = descricao;
        this.inicio = inicio;
        this.fim = fim;
        this.nome = nome;
        this.descricaoLab = descLab;
        this.descricaoSala = descSala;
    }

    public HorariosDTO(String dia, String descricao, Calendar inicio, 
            Calendar fim, String nome) {
        this.dia = dia;
        this.descricao = descricao;
        this.inicio = inicio;
        this.fim = fim;
        this.nome = nome;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public Calendar getInicio() {
        return inicio;
    }

    public void setInicio(Calendar inicio) {
        this.inicio = inicio;
    }

    public Calendar getFim() {
        return fim;
    }

    public void setFim(Calendar fim) {
        this.fim = fim;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValorInicio() {
        return retornaHora(inicio);
    }

    public void setValorInicio(String valorInicio) {
        this.valorInicio = valorInicio;
    }

    public String getValorFim() {
        return retornaHora(fim);
    }

    public void setValorFim(String valorFim) {
        this.valorFim = valorFim;
    }
    
    public String retornaHora(Calendar hora){
        SimpleDateFormat parse = new SimpleDateFormat("HH:mm:ss");
        return parse.format(hora.getTime());
    }

    public String getDescricaoSala() {
        return descricaoSala;
    }

    public void setDescricaoSala(String descricaoSala) {
        this.descricaoSala = descricaoSala;
    }

    public String getDescricaoLab() {
        return descricaoLab;
    }

    public void setDescricaoLab(String descricaoLab) {
        this.descricaoLab = descricaoLab;
    }

    public String getLocal() {
        if(descricaoSala.equals("X")){
            return descricaoLab;
        } else {
            return descricaoSala;
        }
    }

    public void setLocal(String local) {
        this.local = local;
    }  

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.dia);
        hash = 47 * hash + Objects.hashCode(this.inicio);
        hash = 47 * hash + Objects.hashCode(this.fim);
        hash = 47 * hash + Objects.hashCode(this.descricao);
        hash = 47 * hash + Objects.hashCode(this.nome);
        hash = 47 * hash + Objects.hashCode(this.valorInicio);
        hash = 47 * hash + Objects.hashCode(this.valorFim);
        hash = 47 * hash + Objects.hashCode(this.descricaoSala);
        hash = 47 * hash + Objects.hashCode(this.descricaoLab);
        hash = 47 * hash + Objects.hashCode(this.local);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HorariosDTO other = (HorariosDTO) obj;
        if (!Objects.equals(this.dia, other.dia)) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.valorInicio, other.valorInicio)) {
            return false;
        }
        if (!Objects.equals(this.valorFim, other.valorFim)) {
            return false;
        }
        if (!Objects.equals(this.descricaoSala, other.descricaoSala)) {
            return false;
        }
        if (!Objects.equals(this.descricaoLab, other.descricaoLab)) {
            return false;
        }
        if (!Objects.equals(this.local, other.local)) {
            return false;
        }
        if (!Objects.equals(this.inicio, other.inicio)) {
            return false;
        }
        if (!Objects.equals(this.fim, other.fim)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HorariosDTO{" + "dia=" + dia + ", inicio=" + inicio + ", fim=" + fim + ", descricao=" + descricao + ", nome=" + nome + ", valorInicio=" + valorInicio + ", valorFim=" + valorFim + ", descricaoSala=" + descricaoSala + ", descricaoLab=" + descricaoLab + ", local=" + local + '}';
    }
    
}
