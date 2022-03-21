package model.dao;

import java.util.List;

import model.entities.Secretaria;

public interface SecretariaDao {

	void insert(Secretaria obj);

	void update(Secretaria obj);

	void deleteById(Integer id);

	Secretaria findById(Integer id);

	List<Secretaria> findAll();
}
