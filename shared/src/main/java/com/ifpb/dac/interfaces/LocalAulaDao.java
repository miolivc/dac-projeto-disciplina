package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.LocalAula;
import java.util.List;

/**
 *
 * @author miolivc
 */
public interface LocalAulaDao {
    
    void adicionar(LocalAula local);
    void remover(LocalAula local);
    void atualizar(LocalAula local);
    List<LocalAula> listarTodos();
    LocalAula buscarPorId(int id);
    List<LocalAula> listarNomeLaboratorios();
    
}
