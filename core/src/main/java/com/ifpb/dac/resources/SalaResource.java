
package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.LaboratorioDao;
import com.ifpb.dac.interfaces.SalaDao;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("local")
@Produces(MediaType.APPLICATION_JSON)
public class SalaResource {
    
    @Inject
    private SalaDao salaDao;
    
    @Inject
    private LaboratorioDao laboratorioDao; 
    
    @GET
    @Path("sala")
    public Response todasAsSalas() {
        List<String> salas = salaDao.listarNomeSalas();
        if (salas == null || salas.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        return Response.ok().entity(salas).build();
    }
    
    
    @GET
    @Path("laboratorio")
    public Response todosOsLabs() {
        List<String> labs = laboratorioDao.listarNomeLaboratorios();
        if (labs == null || labs.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        return Response.ok().entity(labs).build();
    }
    
}
