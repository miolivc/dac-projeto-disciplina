
package com.ifpb.dac.services;

import javax.ejb.Stateful;
import javax.ejb.Remote;
import com.ifpb.dac.interfaces.GerenciadorMensagem;

@Stateful
@Remote(GerenciadorMensagem.class)
public class GerenciadorMensagemService implements GerenciadorMensagem {
    
}
