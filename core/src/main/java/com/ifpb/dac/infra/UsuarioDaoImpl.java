package com.ifpb.dac.infra;

import com.ifpb.dac.entidades.Usuario;
import com.ifpb.dac.enums.TipoUsuario;
import com.ifpb.dac.interfaces.UsuarioDao;
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
@Remote(UsuarioDao.class)
public class UsuarioDaoImpl implements UsuarioDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void adicionar(Usuario usuario) {
        usuario.setEmail(usuario.getEmail().toLowerCase());
        em.persist(usuario);
    }

    @Override
    public void remover(Usuario usuario) {
        em.remove(buscarPorId(usuario.getId()));
    }

    @Override
    public void atualizar(Usuario usuario) {
        em.merge(usuario);
    }

    @Override
    public List<Usuario> listarTodos() {
        return em.createQuery("SELECT u FROM Usuario u",
                Usuario.class).getResultList();
    }

    @Override
    public Usuario buscarPorId(int id) {
        return em.find(Usuario.class, id);
    }

    @Override
    public Usuario autentica(String email, String senha, TipoUsuario tipo) {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u WHERE "
                + "u.email =:email AND u.senha =:senha AND u.tipo =:tipo", Usuario.class);
        query.setParameter("email", email.toLowerCase());
        query.setParameter("senha", senha);
        query.setParameter("tipo", tipo);
        Optional<Usuario> resultado = query.getResultList().stream().findFirst();
        if (resultado.isPresent()) {
            Usuario usuario = resultado.get();
            return usuario;
        } else {
            return null;
        }
    }

    @Override
    public boolean verificarEmail(String email) {
        TypedQuery<Usuario> createQuery = em.createQuery("SELECT u FROM "
                + "Usuario u WHERE u.email =:email", Usuario.class);
        createQuery.setParameter("email", email);
        Optional<Usuario> findFirst = createQuery.getResultList().stream().findFirst();
        if (findFirst.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

}
