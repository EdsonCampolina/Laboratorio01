package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.MatriculaDao;
import model.entities.Matricula;

public class MatriculaDaoJDBC implements MatriculaDao{

	private Connection conn;

	public MatriculaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Matricula obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Matricula obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Matricula findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Matricula> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
