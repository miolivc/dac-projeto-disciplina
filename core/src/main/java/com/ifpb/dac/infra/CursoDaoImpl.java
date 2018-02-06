package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Curso;
import com.ifpb.dac.interfaces.CursoDao;
import java.util.List;
import java.util.Optional;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
@Remote(CursoDao.class)
public class CursoDaoImpl implements CursoDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void adicionar(Curso curs) {
        em.persist(curs);
    }

    @Override
    public void remover(Curso curs) {
        Curso aux = buscarPorId(curs.getCodigo_curso());
        em.remove(aux);
    }

    @Override
    public void atualizar(Curso curs) {
        em.merge(curs);
    }

    @Override
    public List<Curso> listarTodos() {
        return em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
    }

    @Override
    public Curso buscarPorId(int id) {
        return em.find(Curso.class, id);
    }
    
    @Override
    public List<String> listarNomeCursos(){
        return em.createQuery("SELECT c.info.descricao FROM Curso c", 
                String.class).getResultList();
    }

    @Override
    public Curso retornarPorNome(String curso) {
        TypedQuery<Curso> createQuery = em.createQuery("SELECT c FROM Curso c "
                + "WHERE c.info.descricao =:curso", Curso.class);
        createQuery.setParameter("curso", curso);
        Optional<Curso> findFirst = createQuery.getResultList().stream().findFirst();
        if (findFirst.isPresent()){
            return findFirst.get();
        } else {
            return null;
        }
    }

}
