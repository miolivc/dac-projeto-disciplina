package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Aluno;
import java.util.List;

/**
 *
 * @author rodrigobento
 */
public interface AlunoDao {
    
    void adicionar(Aluno aluno);
    void remover(Aluno aluno);
    void atualizar(Aluno aluno);
    List<Aluno> listarTodos();
    Aluno buscarPorId(int id);
    Aluno autentica(String email, String senha);
    boolean verificarEmail(String email);
    Aluno buscarPorEmail(String email_aluno);
    
}
