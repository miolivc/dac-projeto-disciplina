package com.ifpb.dac.entidades;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Coordenador extends Professor implements Serializable {

    @OneToMany
    private List<Professor> gerenciados = new ArrayList<>();

    public Coordenador() {
    }

    public void addGerenciado(Professor professor) {
        this.gerenciados.add(professor);
    }

    public void removeGerenciado(Professor professor) {
        this.gerenciados.remove(professor);
    }

    public List<Professor> getGerenciados() {
        return gerenciados;
    }

    public void setGerenciados(List<Professor> gerenciados) {
        this.gerenciados = gerenciados;
    }
}
