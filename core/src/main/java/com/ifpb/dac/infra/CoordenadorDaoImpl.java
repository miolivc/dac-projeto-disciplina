package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Coordenador;
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
    public void remover(Coordenador prof) {
        Coordenador auxiliar = buscarPorId(prof.getCodigo());
        em.remove(auxiliar);
    }

    @Override
    public void atualizar(Coordenador coordenador) {
        em.merge(coordenador);
    }

    @Override
    public List<Coordenador> listarTodos() {
        return em.createQuery("SELECT p FROM Coordenador p",
                Coordenador.class).getResultList();
    }

    @Override
    public Coordenador buscarPorId(int id) {
        return em.find(Coordenador.class, id);
    }

    @Override
    public List<String> listarNomeCoordenadores() {
        return em.createQuery("SELECT p.nome FROM Coordenador p",
                String.class).getResultList();
    }

    @Override
    public Coordenador buscarPorNome(String nomeCoordenador) {
        TypedQuery<Coordenador> createQuery = em.createQuery("select p from Coordenador p where" +
                " p.nome like :nomeCoordenador", Coordenador.class);
        createQuery.setParameter("nomeCoordenador", nomeCoordenador);
        Optional<Coordenador> resultado = createQuery.getResultList().stream().findFirst();
        if (resultado.isPresent()) {
            Coordenador prof = resultado.get();
            return prof;
        } else {
            return null;
        }
    }

    @Override
    public Coordenador autentica(String email, String senha) {
        TypedQuery<Coordenador> createQuery =
                em.createQuery("SELECT p FROM Coordenador p WHERE p.email =:email "
                + "AND p.senha =:senha", Coordenador.class);
        createQuery.setParameter("email", email);
        createQuery.setParameter("senha", senha);
        Optional<Coordenador> findFirst = createQuery.getResultList().stream().findFirst();
        if (findFirst.isPresent()) {
            return findFirst.get();
        } else {
            return null;
        }
    }

    @Override
    public boolean verificarEmail(String email) {
        TypedQuery<Coordenador> createQuery = em.createQuery("SELECT p FROM Coordenador p "
                + "WHERE p.email =:email", Coordenador.class);
        createQuery.setParameter("email", email);
        Optional<Coordenador> findFirst = createQuery.getResultList().stream().findFirst();
        return (findFirst.isPresent());
    }
    
}
