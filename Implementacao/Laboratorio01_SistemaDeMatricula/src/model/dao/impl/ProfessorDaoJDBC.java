package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.ProfessorDao;
import model.entities.Professor;

public class ProfessorDaoJDBC implements ProfessorDao {
	
	private Connection conn;

	public ProfessorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Professor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Professor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Professor findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Professor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
