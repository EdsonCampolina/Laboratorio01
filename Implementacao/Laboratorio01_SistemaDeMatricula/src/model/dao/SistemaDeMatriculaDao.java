package model.dao;

import java.util.List;

import model.entities.SistemaDeMatricula;

public interface SistemaDeMatriculaDao {

	void insert(SistemaDeMatricula obj);

	void update(SistemaDeMatricula obj);

	void deleteById(Integer id);

	SistemaDeMatricula findById(Integer id);

	List<SistemaDeMatricula> findAll();
}
