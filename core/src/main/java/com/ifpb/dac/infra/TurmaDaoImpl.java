package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Curso;
import com.ifpb.dac.entidades.Turma;
import com.ifpb.dac.interfaces.TurmaDao;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author rodrigobento
 */
@Stateless
@Remote(TurmaDao.class)
public class TurmaDaoImpl implements TurmaDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void adicionar(Turma t) {
        em.persist(t);
    }

    @Override
    public void atualizar(Turma t) {
        em.merge(t);
    }

    @Override
    public void remover(Turma t) {
        Turma auxiliar = buscarPorId(t.getCodigo_turma());
        em.remove(auxiliar);
    }

    @Override
    public List<Turma> listarTodos() {
        return em.createQuery("SELECT t FROM Turma t", 
                Turma.class).getResultList();
    }

    @Override
    public Turma buscarPorId(int id) {
        return em.find(Turma.class, id);
    }

    @Override
    public List<String> listarTodasDisciplinas() {
        return em.createQuery("SELECT DISTINCT(t.nome_disciplina) FROM Turma t ORDER BY t.nome_disciplina", 
                String.class).getResultList();
    }

    @Override
    public List<String> professoresDisciplina(String disciplina) {
        TypedQuery<String> createQuery = em.createQuery("SELECT p.nome FROM Turma t "
                + "JOIN t.professor p "
                + "WHERE t.nome_disciplina =:disc "
                + "GROUP BY p.codigo", String.class);
        createQuery.setParameter("disc", disciplina);
        return createQuery.getResultList();
    }

    @Override
    public List<String> disciplinaProfessores(String professor) {
        TypedQuery<String> createQuery = em.createQuery("SELECT t.nome_disciplina "
                + "FROM Turma t "
                + "JOIN t.professor p "
                + "WHERE p.nome =:prof "
                + "GROUP BY t.nome_disciplina", String.class);
        createQuery.setParameter("prof", professor);
        List<String> lista = createQuery.getResultList();
        if(lista.stream().findFirst().isPresent()){
            return lista;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public Turma retornarDiscProf(String disciplina, String professor) {
        TypedQuery<Turma> createQuery = em.createQuery("SELECT t "
                + "FROM Turma t "
                + "JOIN t.professor p "
                + "WHERE p.nome =:prof AND t.nome_disciplina =:disc "
                + "GROUP BY t.codigo_turma", Turma.class);
        createQuery.setParameter("disc", disciplina);
        createQuery.setParameter("prof", professor);
        Optional<Turma> findFirst = createQuery.getResultList().stream().findFirst();
        if(findFirst.isPresent()){
            return findFirst.get();
        } else {
            return null;
        }
    }
    
    @Override
    public int verificarAlunoTurma(int idTurma, int idAluno){
        Long resultado;
        Query createNativeQuery = em.createNativeQuery("SELECT count(*) "
                + "FROM aluno_turma "
                + "WHERE codigo_turma = "+ idTurma + " AND id = " + idAluno);
//        createNativeQuery.setParameter(1, idTurma);
//        createNativeQuery.setParameter(2, idAluno);
        resultado = (Long) createNativeQuery.getSingleResult();
        return resultado.intValue();
    }

    @Override
    public List<Turma> listarTodos(Curso curso) {
        return em.createQuery("SELECT t FROM Turma t WHERE t.curso = :curso", Turma.class)
                    .setParameter("curso", curso)
                    .getResultList();
    }
    
}
