
package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.CursoDao;
import java.util.List;
import java.util.stream.Collector;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("curso")
@Produces(MediaType.APPLICATION_JSON)
public class CursoResource {
    
    @EJB
    private CursoDao cursoDao;
    
    @GET
    public Response todos() {
        List<String> nomeCursos = cursoDao.listarNomeCursos();
        if (nomeCursos == null || nomeCursos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        JsonArray collect = nomeCursos.stream()
                .collect(Collector.of(Json::createArrayBuilder, 
                        (t, u) -> t.add(u), 
                        (x, y) -> x.add(y))
                )
                .build();
        return Response.ok().entity(collect).build();
    }
    
}
