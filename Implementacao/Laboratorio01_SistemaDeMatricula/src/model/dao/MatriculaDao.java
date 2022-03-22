package model.dao;

import model.entities.Matricula;
import model.entities.Oferta;

public interface MatriculaDao {

	void insert(Matricula obj);

	void update(Matricula obj);

	//void deleteById(Integer id);
	
	void insertMatriculaOferta(Matricula obj, Oferta oferta);

	Matricula findById(Integer id);

	//List<Matricula> findAll();
}
