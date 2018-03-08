package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Coordenador;

import java.util.List;

public interface CoordenadorDao {

    void adicionar(Coordenador coordenador);
    void remover(Coordenador coordenador);
    void atualizar(Coordenador coordenador);
    List<Coordenador> listarTodos();
    Coordenador buscarPorCodigo(int codigo);
    Coordenador autentica(String email, String senha);
    boolean verificarEmail(String email);

}
