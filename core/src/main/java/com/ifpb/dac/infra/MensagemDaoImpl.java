
package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Mensagem;
import com.ifpb.dac.interfaces.MensagemDao;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Remote(MensagemDao.class)
public class MensagemDaoImpl implements MensagemDao, Serializable {

    @PersistenceContext
    private EntityManager manager;
    
    @Override
    public void adicionar(Mensagem mensagem) {
        manager.persist(mensagem);
    }

    @Override
    public void remover(Mensagem mensagem) {
        manager.remove(mensagem);
    }

    @Override
    public List<Mensagem> listarTodas() {
        return manager.createQuery("FROM Mensagem m", Mensagem.class)
                .getResultList();
    }

    @Override
    public Mensagem buscaPorId(String id) {
        return manager.find(Mensagem.class, id);
    }

    @Override
    public List<Mensagem> mensagensRemetente(int id) {
        return manager.createQuery("FROM Mensagem m WHERE m.remetente.id := id",
                Mensagem.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<Mensagem> mensagensDestinatario(int id) {
        return manager.createQuery("FROM Mensagem m WHERE m.destino.id := id", 
                Mensagem.class)
                .setParameter("id", id)
                .getResultList();
    }
    
}
