package model.dao;

import java.util.List;

import model.entities.Curso;
import model.entities.Disciplina;

public interface CursoDao {

	void insert(Curso obj);

	void update(Curso obj);

	void deleteById(Integer id);

	void insertDisciplina(Curso curso, Disciplina disciplina);

	Curso findById(Integer id);

	List<Curso> findAll();
}
