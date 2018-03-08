package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Mensagem;
import com.ifpb.dac.entidades.Usuario;
import com.ifpb.dac.interfaces.MensagemDao;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@Stateless
@Remote(MensagemDao.class)
public class MensagemDaoImpl implements MensagemDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void salvar(Mensagem msg) {
        manager.persist(msg);
    }

    @Override
    public void deletar(Mensagem msg) {
        manager.remove(msg);
    }

    @Override
    public void atualizar(Mensagem msg) {
        manager.merge(msg);
    }

    @Override
    public List<Mensagem> todas(Usuario remetente, Usuario destino) {
        String sql = "SELECT m FROM Mensagem m WHERE m.remente =:remetente AND m.destino =:destino ORDER BY ASC criadoEm";
        List<Mensagem> result = manager.createQuery(sql, Mensagem.class).getResultList();
        if (result != null && ! result.isEmpty())  return result;
        return Collections.EMPTY_LIST;
    }
}
