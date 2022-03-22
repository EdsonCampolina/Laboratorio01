package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.ProfessorDao;
import model.entities.Professor;

public class ProfessorDaoJDBC implements ProfessorDao {

	private Connection conn;

	public ProfessorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Professor obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO tbprofessores (Nome,Senha) VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setString(2, obj.getSenha());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					obj.setId(rs.getInt(1));
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}

		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(Professor obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE tbprofessores SET Nome = ? ,  Senha = ? WHERE IdProfessor = ?");

			st.setString(1, obj.getNome());
			st.setString(2, obj.getSenha());
			st.setInt(3, obj.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM tbprofessores WHERE IdProfessor = ?");

			st.setInt(1, id);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Professor findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM tbprofessores WHERE IdProfessor = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Professor obj = instantiateProfessor(rs);
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Professor> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM tbcursos");
			rs = st.executeQuery();
			List<Professor> list = new ArrayList<>();
			while (rs.next()) {
				Professor obj = instantiateProfessor(rs);
				list.add(obj);
			}
			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	/*
	 * METODOS DE INSTANCIAÇÃO
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	private Professor instantiateProfessor(ResultSet rs) throws SQLException {
		Professor obj = new Professor();
		obj.setId(rs.getInt("IdProfessor"));
		obj.setNome(rs.getString("Nome"));
		obj.setSenha(rs.getString("Senha"));
		return obj;
	}

}
