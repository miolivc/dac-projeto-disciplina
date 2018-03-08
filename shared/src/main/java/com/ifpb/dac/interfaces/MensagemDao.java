package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Mensagem;
import com.ifpb.dac.entidades.Usuario;

import java.util.List;

public interface MensagemDao {

    void salvar(Mensagem msg);
    void deletar(Mensagem msg);
    void atualizar(Mensagem msg);

    /**
     * Recupera lista de mensagens (histórico) de acordo com o id do remetende e destino
     * @param remetente
     * @param destino
     * @return List<Mensagem>
     */
    List<Mensagem> todas(Usuario remetente, Usuario destino);

}
