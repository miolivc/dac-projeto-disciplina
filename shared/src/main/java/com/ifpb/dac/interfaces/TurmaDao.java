package com.ifpb.dac.interfaces;

import com.ifpb.dac.entidades.Curso;
import com.ifpb.dac.entidades.Professor;
import com.ifpb.dac.entidades.Turma;
import java.util.List;

/**
 *
 * @author rodrigobento
 */
public interface TurmaDao {
    
    void adicionar(Turma t);
    void atualizar(Turma t);
    void remover(Turma t);
    List<Turma> listarTodos();
    Turma buscarPorId(int id);
    List<String> listarTodasDisciplinas();
    List<String> professoresDisciplina(String disciplina);
    List<String> disciplinaProfessores(String professor);
    Turma retornarDiscProf(String disciplina, String professor);
    int verificarAlunoTurma(int idTurma, int idAluno);
    List<Turma> listarTodos(Curso curso);
    
}
