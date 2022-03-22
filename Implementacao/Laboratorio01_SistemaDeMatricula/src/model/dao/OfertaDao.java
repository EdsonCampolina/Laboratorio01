package model.dao;

import java.util.List;

import model.entities.Oferta;

public interface OfertaDao {

	void insert(Oferta obj);

	void update(Oferta obj);

	// void deleteById(Integer id);

	void insertAluno(Integer idOferta, Integer idAluno);

	void removeAluno(Integer idOferta, Integer idAluno);

	Oferta findById(Integer id);

	List<Oferta> findAll();
}
