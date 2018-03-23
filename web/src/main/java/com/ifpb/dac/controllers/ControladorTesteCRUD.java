package com.ifpb.dac.controllers;

import com.ifpb.dac.entidades.Aula;
import com.ifpb.dac.entidades.Curso;
import com.ifpb.dac.entidades.Disciplina;
import com.ifpb.dac.entidades.Horario;
import com.ifpb.dac.entidades.Info;
import com.ifpb.dac.entidades.Laboratorio;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.entidades.Sala;
import com.ifpb.dac.entidades.Turma;
import com.ifpb.dac.enums.Regime;
import com.ifpb.dac.enums.Unidade;
import com.ifpb.dac.enums.Vinculo;
import com.ifpb.dac.interfaces.AulaDao;
import com.ifpb.dac.interfaces.CursoDao;
import com.ifpb.dac.interfaces.DisciplinaDao;
import com.ifpb.dac.interfaces.HorarioDao;
import com.ifpb.dac.interfaces.LaboratorioDao;
import com.ifpb.dac.interfaces.ProfessorDao;
import com.ifpb.dac.interfaces.SalaDao;
import com.ifpb.dac.interfaces.TurmaDao;
import java.util.Calendar;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class ControladorTesteCRUD {  
    
    @Inject
    private AulaDao aulaDao;
    @Inject
    private SalaDao salaDao;
    @Inject
    private LaboratorioDao labDao;
    @Inject
    private TurmaDao turmaDao;
    @Inject
    private ProfessorDao profDao;
    @Inject
    private DisciplinaDao discDao;
    @Inject
    private CursoDao cursoDao;
    @Inject
    private HorarioDao horaDao;
    
    private Info info = new Info("TESTE", "TESTE");
    private Info f = new Info("XXX", "XXX");
    private Sala sala = new Sala("TESTE", "TESTE");
    private Laboratorio lab = new Laboratorio("TESTE", "TESTE");
    private Professor prof = new Professor();
    private Horario hora = new Horario("XXX", Calendar.getInstance(), Calendar.getInstance());
    private Curso curso = new Curso(info, 9, "TESTE");
    private Disciplina disc = new Disciplina("TESTE", "TESTE", 2, 5, curso);
    private Turma turma = new Turma("X", "XXX", curso, prof);
    private Aula aula = new Aula();
    
    public String addSala(){
        salaDao.adicionar(sala);
        return null;
    }
    
    public String removerSala(){
        salaDao.remover(salaDao.buscarPorId(3));
        return null;
    }
    
    public String atualizarSala(){
        sala.setCodigo_sala(4);
        sala.setAbreviacao("TESTE");
        sala.setDescricao("TESTE");
        salaDao.atualizar(sala);
        return null;
    }
    
    public String addLab(){
        labDao.adicionar(lab);
        return null;
    }
    
    public String removerLab(){
        labDao.remover(labDao.buscarPorId(3));
        return null;
    }
    
    public String atualizarLab(){
        lab.setCodigo_lab(4);
        labDao.atualizar(lab);
        return null;
    }
    
    public String addProf(){
//        profDao.adicionar(prof);
        return null;
    }
    public String removerProf(){
//        prof.setCodigo(10);
//        profDao.remover(prof);
        return null;
    }
    public String atualizarProf(){
//        prof.setEmail("XXX");
//        prof.setNome("XXX");
//        prof.setCodigo(5);
//        profDao.atualizar(prof);
        return null;
    }
    
    public String addTurma(){
        Curso aux = cursoDao.buscarPorId(21);
        turma.setCurso(aux);
        turma.setProfessor(profDao.buscarPorId(7));
        turmaDao.adicionar(turma);
        return null;
    }
    public String removerTurma(){
        turmaDao.remover(turmaDao.buscarPorId(20921));
        return null;
    }
    
    public String atualizarTurma(){
        turma.setCodigo_turma(20920);
        turma.setIdentificacao("U");
        turma.setNome_disciplina("XXX");
        turmaDao.atualizar(turma);
        return null;
    }
    
    public String addCurso(){
        cursoDao.adicionar(curso);
        return null;
    }
    
    public String removerCurso(){
        cursoDao.remover(cursoDao.buscarPorId(20));
        return null;
    }
    
    public String atualizarCurso(){
        curso.setCodigo_curso(10);
        curso.setPeriodo(999999999);
        curso.setUnidade("TESTE");
        cursoDao.atualizar(curso);
        return null;
    }
    
    public String addDisc(){
        disc.setCurso(cursoDao.buscarPorId(21));
        discDao.adicionar(disc);
        return null;
    }
    
    public String removerDisc(){
        discDao.remover(discDao.buscarPorId(8264));
        return null;
    }
    
    public String atualizarDisc(){
        disc.setCodigo_disc(8262);
        disc.setAulas_semana(10);
        disc.setCarga_horaria(10);
        discDao.atualizar(disc);
        return null;
    }
    
    public String addAula(){
        aula.setDia("XXX");
        aula.setAbrev_dia(1);
        aula.setCod_aula(1500);
        aula.setCurso(cursoDao.buscarPorId(11));
        aula.setDisciplina(discDao.buscarPorId(8265));
        aula.setHorario(horaDao.buscarPorId(5));
        aula.setLaboratorio(labDao.buscarPorId(5));
        aula.setSala(salaDao.buscarPorId(6));
        aula.setProfessor(profDao.buscarPorId(16));
        aula.setTurma(turmaDao.buscarPorId(20923));
        aulaDao.adicionar(aula);
        return null;
    }
    
    public String removerAula(){
        aulaDao.remover(aulaDao.buscarPorId(5));
        return null;
    }
    
    public String atualizarAula(){
        aula.setDia("XXXXXXX");
        aula.setCod_aula(5);
        aulaDao.atualizar(aula);
        return null;
    }
    
    public String addHora(){
        horaDao.adicionar(hora);
        return null;
    }
    
    public String removerHora(){
        horaDao.remover(horaDao.buscarPorId(3));
        return null;
    }
    
    public String atualizarHora(){
        hora.setCodigo_hora(2);
        hora.setDescricao("TESTE");
        horaDao.atualizar(hora);
        return null;
    }
    
}
    