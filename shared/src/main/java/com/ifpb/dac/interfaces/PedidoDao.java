package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Curso;
import com.ifpb.dac.entidades.Pedido;
import com.ifpb.dac.enums.TipoUsuario;
import java.util.List;

/**
 *
 * @author rodrigobento
 */
public interface PedidoDao {
    
    void adicionar(Pedido p);
    void remover(Pedido p);
    void atualizar(Pedido p);
    List<Pedido> listarTodos();
    Pedido buscarPorId(int id);
    Pedido buscarPorCredenciais(String email, String senha);
    List<Pedido> listarPedidosPorTipoUsuario(TipoUsuario tipo);
    List<Pedido> listarPedidosPorCurso(Curso curso);
    
}
