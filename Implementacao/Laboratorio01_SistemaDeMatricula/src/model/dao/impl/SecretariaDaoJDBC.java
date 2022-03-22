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
import model.dao.SecretariaDao;
import model.entities.Secretaria;

public class SecretariaDaoJDBC implements SecretariaDao {

	private Connection conn;

	public SecretariaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Secretaria obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO tbsecretaria (Nome,Senha) VALUES (?, ?)",
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
	public void update(Secretaria obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE tbsecretaria SET Nome = ? ,  Senha = ? WHERE IdSecretaria = ?");

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
			st = conn.prepareStatement("DELETE FROM tbsecretaria WHERE IdSecretaria = ?");

			st.setInt(1, id);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Secretaria findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM tbsecretaria WHERE IdSecretaria = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Secretaria obj = instantiateSecretaria(rs);
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
	public List<Secretaria> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM tbcursos");
			rs = st.executeQuery();
			List<Secretaria> list = new ArrayList<>();
			while (rs.next()) {
				Secretaria obj = instantiateSecretaria(rs);
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

	private Secretaria instantiateSecretaria(ResultSet rs) throws SQLException {
		Secretaria obj = new Secretaria();
		obj.setId(rs.getInt("IdSecretaria"));
		obj.setNome(rs.getString("Nome"));
		obj.setSenha(rs.getString("Senha"));
		return obj;
	}
}
