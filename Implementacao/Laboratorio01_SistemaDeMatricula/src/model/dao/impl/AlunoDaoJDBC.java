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
import model.dao.AlunoDao;
import model.entities.Aluno;

public class AlunoDaoJDBC implements AlunoDao {

	private Connection conn;

	public AlunoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Aluno obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO tbalunos (Nome,IdMatricula,Senha) VALUES (?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setInt(2, obj.getIdMatricula());
			st.setString(3, obj.getSenha());

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
	public void update(Aluno obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE tbalunos SET Nome = ? ,  Senha = ?, IdMatricula= ? WHERE IdAluno = ?");

			st.setString(1, obj.getNome());
			st.setString(2, obj.getSenha());
			st.setInt(3, obj.getIdMatricula());
			st.setInt(4, obj.getId());

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
			st = conn.prepareStatement("DELETE FROM tbalunos WHERE IdAluno = ?");

			st.setInt(1, id);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Aluno findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM tbalunos WHERE IdAluno = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Aluno obj = instantiateAluno(rs);
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
	public List<Aluno> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM tbalunos");
			rs = st.executeQuery();
			List<Aluno> list = new ArrayList<>();
			while (rs.next()) {
				Aluno obj = instantiateAluno(rs);
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

	private Aluno instantiateAluno(ResultSet rs) throws SQLException {
		Aluno obj = new Aluno();
		obj.setId(rs.getInt("IdAluno"));
		obj.setNome(rs.getString("Nome"));
		obj.setIdMatricula(rs.getInt("IdMatricula"));
		obj.setSenha(rs.getString("Senha"));
		return obj;
	}

}
