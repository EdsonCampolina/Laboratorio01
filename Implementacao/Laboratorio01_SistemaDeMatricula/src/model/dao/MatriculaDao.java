package model.dao;

import java.util.List;

import model.entities.Matricula;

public interface MatriculaDao {

	void insert(Matricula obj);

	void update(Matricula obj);

	void deleteById(Integer id);

	Matricula findById(Integer id);

	List<Matricula> findAll();
}
