
package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Curso;
import com.ifpb.dac.entidades.Pedido;
import com.ifpb.dac.enums.TipoUsuario;
import com.ifpb.dac.interfaces.PedidoDao;
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
@Remote(PedidoDao.class)
public class PedidoDaoImpl implements PedidoDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void adicionar(Pedido p) {
        em.persist(p);
    }

    @Override
    public void remover(Pedido p) {
        Pedido auxiliar = buscarPorId(p.getId());
        em.remove(auxiliar);
    }

    @Override
    public void atualizar(Pedido p) {
//        System.out.println(p.getId() + " seu id");
//        System.out.println(p.getPrioridade() + " para atualizar");
        em.merge(p);
    }

    @Override
    public List<Pedido> listarTodos() {
        return em.createQuery("SELECT p FROM Pedido p ORDER BY p.prioridade DESC", 
                Pedido.class).getResultList();
    }
    
    @Override
    public List<Pedido> listarPedidosPorTipoUsuario(TipoUsuario tipo) {
        TypedQuery<Pedido> createQuery = em.createQuery("SELECT p FROM Pedido p "
                + "WHERE p.tipo =:tipoUsuario ORDER BY p.prioridade DESC", Pedido.class);
        createQuery.setParameter("tipoUsuario", tipo);
        return createQuery.getResultList();
    }

    @Override
    public Pedido buscarPorId(int id) {
        return em.find(Pedido.class, id);
    }

    @Override
    public Pedido buscarPorCredenciais(String email, String senha) {
        TypedQuery<Pedido> createQuery = em.createQuery("SELECT p "
                + "FROM Pedido p WHERE p.email =:email AND p.senha =:senha", Pedido.class);
        createQuery.setParameter("email", email);
        createQuery.setParameter("senha", senha);
        Optional<Pedido> objeto = createQuery.getResultList().stream().findFirst();
        if(objeto.isPresent()){
            return objeto.get();
        } else {
            return null;
        }
   }

    @Override
    public List<Pedido> listarPedidosPorCurso(Curso curso) {
//        String sql = "SELECT new com.ifpb.dac.entidades.Pedido(p.nome, p.email, p.senha, p.tipoUsuario, p.prioridade)"
//                + " FROM Pedido p JOIN Aluno a ON p.email = a.email JOIN Professor f ON p.email = f.email "
//                + "WHERE a.curso = :curso AND f.curso.turmas = ANY :turmas";
        String sql = "SELECT p FROM Pedido p, Aluno a WHERE a.email = p.email AND a.curso = :curso ";
        List<Pedido> alunos = em.createQuery(sql, Pedido.class)
                .setParameter("curso", curso)
                .getResultList();
        
//        sql = "SELECT p FROM Pedido p, Professor a WHERE a.email = p.email AND p = ANY "
//                + "(SELECT j FROM PROFESSOR j WHERE j = :turmas.professor)";
//        List<Pedido> profs = em.createQuery(sql, Pedido.class)
//                .setParameter("turmas", curso.getTurmas())
//                .getResultList();\
        
//        alunos.addAll(profs);
        return alunos;
    }
    
}
