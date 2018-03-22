package com.ifpb.dac.resources.security;

import com.ifpb.dac.entidades.Usuario;
import com.ifpb.dac.enums.TipoUsuario;
import com.ifpb.dac.interfaces.UsuarioDao;
import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Map;
import static javax.ws.rs.core.Response.*;

@Provider
public class AuthorizationFilter implements ContainerRequestFilter {
    
    @EJB
    private UsuarioDao usuarioDao;
    
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        if (requestContext.getUriInfo().getPath().contains("aluno/authenticate")) {
            return;
        }
        
        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || authHeader.isEmpty() || (! authHeader.contains("Basic "))) {
            requestContext.abortWith(Response.status(Status.UNAUTHORIZED)
                    .entity("{'msg':'unauthorized'}")
                    .build());
        }
        
        Map<String, String> decode = BasicAuth.decode(authHeader);
        Usuario autentica = usuarioDao.autentica(decode.get("email"), 
                decode.get("password"), TipoUsuario.Aluno);
        
        if (autentica == null) {
            requestContext.abortWith(Response.status(Status.UNAUTHORIZED)
                    .entity("{'msg':'unauthorized'}")
                    .build());
        }
    }

}
