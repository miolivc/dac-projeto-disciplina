package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Coordenador;

import java.util.List;

/**
 *
 * @author miolivc
 */
public interface CoordenadorDao {
    
    void adicionar(Coordenador coordenador);
    void remover(Coordenador coordenador);
    void atualizar(Coordenador coordenador);
    List<Coordenador> listarTodos();
    Coordenador buscarPorNome(String nomeCoordenador);
    List<String> listarNomeCoordenadores();
    Coordenador buscarPorId(int id);
    Coordenador autentica(String email, String senha);
    boolean verificarEmail(String email);
            
}
