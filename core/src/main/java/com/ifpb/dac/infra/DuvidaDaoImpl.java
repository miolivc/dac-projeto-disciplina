/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Duvida;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.interfaces.DuvidaDao;
import java.util.List;
import java.util.Optional;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author natan
 */
@Remote(DuvidaDao.class)
@Stateless
public class DuvidaDaoImpl implements DuvidaDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void adicionar(Duvida duvida) {
        em.persist(duvida);
    }

    @Override
    public void remover(Duvida duvida) {
        em.remove(duvida);
    }

    @Override
    public void atualizar(Duvida duvida) {
        Duvida d = em.find(Duvida.class, duvida.getId());
        d.setResposta(duvida.getResposta());
    }

    @Override
    public List<Duvida> listarTodos() {
        TypedQuery<Duvida> createQuery = em.createQuery("select d from Duvida d", Duvida.class);
        return createQuery.getResultList();
    }

    @Override
    public Duvida buscarPorId(int id) {
        return em.find(Duvida.class, id);
    }

    @Override
    public List<Duvida> buscarPorProfessor(Professor professor) {
        TypedQuery<Duvida> createQuery = em.createQuery("select d from Duvida d where d.professor.codigo = :codigo_professor", Duvida.class);
        createQuery.setParameter("codigo_professor", professor.getCodigo());
        return createQuery.getResultList();
    }

    @Override
    public List<Duvida> buscarPorProfessorEDuvidaNaoRespondida(int codigoProfessor) {
        System.out.println("Codigo professor: " + codigoProfessor);
        TypedQuery<Duvida> createQuery = em.createQuery("select d from Duvida d where d.resposta is null and d.professor.codigo = :codigo_professor", Duvida.class);
        createQuery.setParameter("codigo_professor", codigoProfessor);

        return createQuery.getResultList();
    }

}
