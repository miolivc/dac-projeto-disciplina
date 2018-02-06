package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Mensagem;
import java.util.List;

public interface MensagemDao {
        
    void adicionar(Mensagem mensagem);
    void remover(Mensagem mensagem);
    List<Mensagem> listarTodas();
    Mensagem buscaPorId(String id);
    List<Mensagem> mensagensRemetente(int id);
    List<Mensagem> mensagensDestinatario(int id);
    
}
