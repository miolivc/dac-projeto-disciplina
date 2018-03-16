
package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.LaboratorioDao;
import com.ifpb.dac.interfaces.SalaDao;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
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
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        GenericEntity answer = new GenericEntity<List<String>>(salas){};
        return Response.ok().entity(answer).build();
    }
    
    
    @GET
    @Path("laboratorio")
    public Response todosOsLabs() {
        List<String> labs = laboratorioDao.listarNomeLaboratorios();
        if (labs == null || labs.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        GenericEntity answer = new GenericEntity<List<String>>(labs){};
        return Response.ok().entity(answer).build();
    }
    
}
