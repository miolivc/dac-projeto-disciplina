package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Coordenador;
import com.ifpb.dac.interfaces.AlunoDao;
import com.ifpb.dac.interfaces.CoordenadorDao;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Stateless
@Remote(CoordenadorDao.class)
public class CoordenadorDaoImpl implements CoordenadorDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void adicionar(Coordenador coordenador) {
        coordenador.setEmail(coordenador.getEmail().toLowerCase());
        em.persist(coordenador);
    }

    @Override
    public void remover(Coordenador coordenador) {
        em.remove(buscarPorCodigo(coordenador.getCodigo()));
    }

    @Override
    public void atualizar(Coordenador coordenador) {
        em.merge(coordenador);
    }

    @Override
    public List<Coordenador> listarTodos() {
        return em.createQuery("SELECT c FROM Coordenador c", Coordenador.class).getResultList();
    }

    @Override
    public Coordenador buscarPorCodigo(int codigo) {
        return em.find(Coordenador.class, codigo);
    }

    @Override
    public Coordenador autentica(String email, String senha) {
        TypedQuery<Coordenador> query = em.createQuery("SELECT a FROM Coordenador a WHERE "
                + "a.email =:email AND a.senha =:senha", Coordenador.class);
        query.setParameter("email", email.toLowerCase());
        query.setParameter("senha", senha);
        Optional<Coordenador> resultado = query.getResultList().stream().findFirst();
        if(resultado.isPresent()){
            Coordenador usuario = resultado.get();
            return usuario;
        }
        return null;
    }
    
    @Override
    public boolean verificarEmail(String email) {
        TypedQuery<Coordenador> createQuery = em.createQuery("SELECT a FROM "
                + "Coordenador a WHERE a.email =:email", Coordenador.class);
        createQuery.setParameter("email", email);
        Optional<Coordenador> findFirst = createQuery.getResultList().stream().findFirst();
        return findFirst.isPresent();
    }
    
}
