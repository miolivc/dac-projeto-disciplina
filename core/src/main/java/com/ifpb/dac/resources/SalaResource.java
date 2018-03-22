
package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.LaboratorioDao;
import com.ifpb.dac.interfaces.SalaDao;
import java.util.List;
import java.util.stream.Collector;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("local")
@Produces(MediaType.APPLICATION_JSON)
public class SalaResource {
    
    @EJB
    private SalaDao salaDao;
    @EJB
    private LaboratorioDao laboratorioDao; 
    
    @GET
    @Path("sala")
    public Response todasAsSalas() {
        List<String> salas = salaDao.listarNomeSalas();
        if (salas == null || salas.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        JsonArray collect = salas.stream()
                .collect(Collector.of(Json::createArrayBuilder, 
                        (t, u) -> t.add(u), 
                        (x, y) -> x.add(y)))
                .build();
        return Response.ok().entity(collect).build();
    }
    
    
    @GET
    @Path("laboratorio")
    public Response todosOsLabs() {
        List<String> labs = laboratorioDao.listarNomeLaboratorios();
        if (labs == null || labs.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        JsonArray collect = labs.stream()
                .collect(Collector.of(Json::createArrayBuilder, 
                        (t, u) -> t.add(u), 
                        (x, y) -> x.add(y)))
                .build();
        return Response.ok().entity(labs).build();
    }
    
}
