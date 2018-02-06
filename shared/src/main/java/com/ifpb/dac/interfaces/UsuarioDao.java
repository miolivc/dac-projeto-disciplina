package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Usuario;
import com.ifpb.dac.enums.TipoUsuario;
import java.util.List;

/**
 *
 * @author rodrigobento
 */
public interface UsuarioDao {
    
    void adicionar(Usuario usuario);
    void remover(Usuario usuario);
    void atualizar(Usuario usuario);
    List<Usuario> listarTodos();
    Usuario buscarPorId(int id);
    Usuario autentica(String email, String senha, TipoUsuario tipo);
    boolean verificarEmail(String email);
    
}
