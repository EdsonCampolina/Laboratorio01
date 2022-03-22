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
import model.dao.DisciplinaDao;
import model.entities.Disciplina;

public class DisciplinaDaoJDBC implements DisciplinaDao {

	private Connection conn;

	public DisciplinaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Disciplina obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO tbdisciplinas (Nome,Obrigatoria) VALUES (?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setBoolean(2, obj.isObrigatoria());

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
	public void update(Disciplina obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE tbdisciplinas SET Nome = ? ,  Obrigatoria = ? WHERE IdDisciplina = ?");

			st.setString(1, obj.getNome());
			st.setBoolean(2, obj.isObrigatoria());
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
			st = conn.prepareStatement("DELETE FROM tbdisciplinas WHERE IdDisciplina = ?");

			st.setInt(1, id);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Disciplina findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM tbdisciplinas WHERE IdDisciplina = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Disciplina disciplina = instantiateDisciplina(rs);
				return disciplina;
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
	public List<Disciplina> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM tbdisciplinas");
			rs = st.executeQuery();
			List<Disciplina> list = new ArrayList<>();
			while (rs.next()) {
				Disciplina curso = instantiateDisciplina(rs);
				list.add(curso);
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

	private Disciplina instantiateDisciplina(ResultSet rs) throws SQLException {
		Disciplina obj = new Disciplina();
		obj.setId(rs.getInt("IdDisciplina"));
		obj.setNome(rs.getString("Nome"));
		obj.setObrigatoria(rs.getBoolean("Obrigatoria"));

		return obj;
	}

}
