
package com.ifpb.dac.resources;

import com.ifpb.dac.interfaces.TurmaDao;
import java.util.List;
import java.util.stream.Collector;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("turma")
@Produces(MediaType.APPLICATION_JSON)
public class TurmaResource {
    
    @EJB
    private TurmaDao turmaDao;
    
    @GET
    public Response todasAsTurmas() {
        List<String> turmas = turmaDao.listarTodasDisciplinas();
        if (turmas == null || turmas.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        JsonArray collect = turmas.stream()
                .collect(Collector.of(Json::createArrayBuilder, 
                        (t, u) -> t.add(u), 
                        (x, y) -> x.add(y)))
                .build();
        return Response.ok(collect).build();
    }
    
    @GET
    @Path("professores/{disciplina}")
    public Response todosOsProfessores(@PathParam("disciplina") String disciplina) {
        List<String> professores = turmaDao.professoresDisciplina(disciplina);
        if (professores == null || professores.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        JsonArray collect = professores.stream()
                .collect(Collector.of(Json::createArrayBuilder, 
                        (t, u) -> t.add(u), 
                        (x, y) -> x.add(y)))
                .build();
        return Response.ok(collect).build();
    }
    
    @GET
    @Path("disciplinas/{professor}")
    public Response disciplinaProfessores(@PathParam("professor") String professor) {
        List<String> disciplinas = turmaDao.professoresDisciplina(professor);
        if (disciplinas == null || disciplinas.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
      JsonArray collect = disciplinas.stream()
                .collect(Collector.of(Json::createArrayBuilder, 
                        (t, u) -> t.add(u), 
                        (x, y) -> x.add(y)))
                .build();
        return Response.ok(collect).build();
    }
    
}
