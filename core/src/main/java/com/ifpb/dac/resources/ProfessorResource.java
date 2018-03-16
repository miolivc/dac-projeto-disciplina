/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.dac.resources;

import com.ifpb.dac.entidades.PerfilProfessor;
import com.ifpb.dac.interfaces.ProfessorDao;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author miolivc
 */
@Stateless
@Path("professor")
public class ProfessorResource {
    
    @EJB
    private ProfessorDao professorDao;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response todos() {
        List<PerfilProfessor> professores = professorDao.todosOsPerfisProfessores();
        GenericEntity answer = new GenericEntity<List<PerfilProfessor>>(professores){};
        return Response.ok().entity(answer).build();
    }
    
}
