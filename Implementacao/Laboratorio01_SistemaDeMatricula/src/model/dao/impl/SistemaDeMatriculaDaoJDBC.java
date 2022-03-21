package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.SistemaDeMatriculaDao;
import model.entities.SistemaDeMatricula;

public class SistemaDeMatriculaDaoJDBC implements SistemaDeMatriculaDao {

	private Connection conn;

	public SistemaDeMatriculaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(SistemaDeMatricula obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(SistemaDeMatricula obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SistemaDeMatricula findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SistemaDeMatricula> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
