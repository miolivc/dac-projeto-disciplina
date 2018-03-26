
package com.ifpb.dac.resources;

import com.ifpb.dac.entidades.Aluno;
import com.ifpb.dac.entidades.Pedido;
import com.ifpb.dac.interfaces.AlunoDao;
import com.ifpb.dac.interfaces.PedidoDao;
import com.ifpb.dac.resources.security.BasicAuth;
import java.io.UnsupportedEncodingException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Stateless
@Path("aluno")
public class AlunoResource {
    
    @EJB
    private AlunoDao alunoDao;
    @EJB
    private PedidoDao pedidoDao;
    
    @POST
    @Path("authenticate")
    public Response authenticate(@FormParam("email") String email, 
            @FormParam("password") String password) throws UnsupportedEncodingException {
        
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        Aluno aluno = alunoDao.autentica(email, password);
        if (aluno == null || ! aluno.isLogado()) {
            Pedido pedido = pedidoDao.buscarPorCredenciais(email, password);
            pedido.setPrioridade(pedido.getPrioridade() + 1);
            pedidoDao.atualizar(pedido);
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        String authorization = BasicAuth.encode(email, password);
        String answer = "{'Token': " + authorization + "}";
        
        return Response.ok()
                .entity(answer)
                .build();
    }
      
}
