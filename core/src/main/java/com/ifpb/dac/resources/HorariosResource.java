
package com.ifpb.dac.resources;

import com.ifpb.dac.entidades.HorariosDTO;
import com.ifpb.dac.interfaces.HorariosDao;
import com.ifpb.dac.interfaces.SalaDao;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("horarios")
@Produces(MediaType.APPLICATION_JSON)
public class HorariosResource {
    
    @EJB
    private SalaDao salaDao;
    @EJB
    private HorariosDao horariosDao;
    
    @GET
    @Path("sala/{sala}")
    public Response horarioPorTurma(@PathParam("sala") String sala) {

        if (sala == null || sala.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        List<HorariosDTO> horarios = horariosDao.listarHorarioSala(sala);
        if (horarios == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        GenericEntity answer = new GenericEntity<List<HorariosDTO>>(horarios){};
        return Response.ok().entity(answer).build();
    }
    
    @GET
    @Path("professor/{professor}")
    public Response horarioPorProfessor(@PathParam("professor") String professor) {
        
        if (professor == null || professor.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        List<HorariosDTO> horarios = horariosDao.listarHorarioProf(professor);
        if (horarios == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } 
        
        GenericEntity answer = new GenericEntity<List<HorariosDTO>>(horarios){};
        return Response.ok().entity(answer).build();
    }
    
    @GET
    @Path("laboratorio/{local}")
    public Response horariosPorLab(@PathParam("local") String local) {
        
        if (local == null || local.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        List<HorariosDTO> horarios = horariosDao.listarHorarioLab(local);
        if (horarios == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        GenericEntity answer = new GenericEntity(horarios){};
        return Response.ok().entity(answer).build();
    }
    
    @GET
    @Path("curso/{horario}&{disciplina}")
    public Response horariosPorCurso(@PathParam("curso") String curso, 
            @PathParam("disciplina") String disciplina) {
        
        if (curso == null || curso.isEmpty() || disciplina == null || disciplina.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        List<HorariosDTO> horarios = horariosDao.listarHorarioCurso(curso, disciplina);
        if (horarios == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        GenericEntity answer = new GenericEntity<List<HorariosDTO>>(horarios){};
        return Response.ok().entity(answer).build();
    }
    
    @GET
    @Path("turma")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response horariosPorTurma(String disciplina, String professor) {
        
        if (professor == null || professor.isEmpty() || disciplina == null || disciplina.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
        List<HorariosDTO> horarios = horariosDao.listarHorarioTurma(disciplina, professor);
        if (horarios == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        GenericEntity answer = new GenericEntity<List<HorariosDTO>>(horarios){};
        return Response.ok().entity(answer).build();
    }
    
}
