/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifpb.dac.resources;

import com.ifpb.dac.entidades.PerfilProfessor;
import com.ifpb.dac.interfaces.ProfessorDao;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author miolivc
 */
@Stateless
@Path("professor")
public class ProfessorResource {
    
    @Inject
    private ProfessorDao professorDao;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response todos() {
        List<PerfilProfessor> professores = professorDao.todosOsPerfisProfessores();
        return Response.ok().entity(professores).build();
    }
    
}
