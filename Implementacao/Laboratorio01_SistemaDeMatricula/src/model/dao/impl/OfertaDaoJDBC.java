package model.dao.impl;

import java.sql.Connection;
import java.util.List;

import model.dao.OfertaDao;
import model.entities.Oferta;

public class OfertaDaoJDBC implements OfertaDao{

	private Connection conn;

	public OfertaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Oferta obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Oferta obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Oferta findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Oferta> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
