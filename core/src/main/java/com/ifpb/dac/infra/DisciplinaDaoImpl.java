package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Curso;
import com.ifpb.dac.entidades.Disciplina;
import com.ifpb.dac.interfaces.DisciplinaDao;
import java.util.List;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
@Remote(DisciplinaDao.class)
public class DisciplinaDaoImpl implements DisciplinaDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void adicionar(Disciplina disc) {
        em.persist(disc);
    }

    @Override
    public void remover(Disciplina disc) {
//        Disciplina aux = buscarPorId(disc.getCodigo_disc());
        em.remove(disc);
    }

    @Override
    public void atualizar(Disciplina disc) {
        em.merge(disc);
    }

    @Override
    public List<Disciplina> listarTodos() {
        return em.createQuery("SELECT d FROM Disciplina d", Disciplina.class).
                getResultList();
    }
    
    @Override
    public List<Disciplina> listarTodos(Curso curso) {
        return em.createQuery("SELECT d FROM Disciplina d WHERE D.curso =:curso", Disciplina.class)
                .setParameter("curso", curso)
                .getResultList();
    }

    @Override
    public Disciplina buscarPorId(int id) {
        return em.find(Disciplina.class, id);
    }
    
    @Override
    public List<String> listarDisciplinaCurso(String curso) {
        TypedQuery<String> createQuery = em.createQuery("SELECT d.descricao FROM Disciplina d "
                + "JOIN d.curso c "
                + "WHERE c.info.descricao =:curso "
                + "GROUP BY d.descricao", String.class);
        createQuery.setParameter("curso", curso);
        return createQuery.getResultList();
    }

    @Override
    public List<String> listarNomeDisciplinas() {
        return em.createQuery("SELECT d.descricao FROM Disciplina d", 
                String.class).getResultList();
    }
    
}
