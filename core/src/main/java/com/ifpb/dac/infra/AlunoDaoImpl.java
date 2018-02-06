package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Usuario;
import com.ifpb.dac.interfaces.AlunoDao;
import java.util.List;
import java.util.Optional;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author rodrigobento
 */
@Stateless
@Remote(AlunoDao.class)
public class AlunoDaoImpl implements AlunoDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void adicionar(Aluno aluno) {
        aluno.setEmail(aluno.getEmail().toLowerCase());
        em.persist(aluno);
    }

    @Override
    public void remover(Aluno aluno) {
        em.remove(buscarPorId(aluno.getId()));
    }

    @Override
    public void atualizar(Aluno aluno) {
        em.merge(aluno);
    }

    @Override
    public List<Aluno> listarTodos() {
        return em.createQuery("SELECT a FROM Aluno a", Aluno.class).getResultList();
    }

    @Override
    public Aluno buscarPorId(int id) {
        return em.find(Aluno.class, id);
    }

    @Override
    public Aluno autentica(String email, String senha) {
        TypedQuery<Aluno> query = em.createQuery("SELECT a FROM Aluno a WHERE "
                + "a.email =:email AND a.senha =:senha", Aluno.class);
        query.setParameter("email", email.toLowerCase());
        query.setParameter("senha", senha);
        Optional<Aluno> resultado = query.getResultList().stream().findFirst();
        if(resultado.isPresent()){
            Aluno usuario = resultado.get();
            return usuario;
        } else {
            return null;
        }
    }
    
    @Override
    public boolean verificarEmail(String email) {
        TypedQuery<Aluno> createQuery = em.createQuery("SELECT a FROM "
                + "Aluno a WHERE a.email =:email", Aluno.class);
        createQuery.setParameter("email", email);
        Optional<Aluno> findFirst = createQuery.getResultList().stream().findFirst();
        return findFirst.isPresent();
    }
    
}
