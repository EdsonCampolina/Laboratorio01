package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.DisciplinaDao;
import model.entities.Disciplina;

public class DisciplinaDaoJDBC implements DisciplinaDao{

	private Connection conn;

	public DisciplinaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Disciplina obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Disciplina obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Disciplina findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Disciplina> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
