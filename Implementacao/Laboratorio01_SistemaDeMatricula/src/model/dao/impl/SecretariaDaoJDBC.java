package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.SecretariaDao;
import model.entities.Secretaria;

public class SecretariaDaoJDBC implements SecretariaDao{

	private Connection conn;

	public SecretariaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Secretaria obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Secretaria obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Secretaria findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Secretaria> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
