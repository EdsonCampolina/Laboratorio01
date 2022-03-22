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
import model.dao.CursoDao;
import model.entities.Curso;

public class CursoDaoJDBC implements CursoDao {

	private Connection conn;

	public CursoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Curso obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO tbcursos (Nome,Creditos,qntdDisciplinasObrigatorias, qntdDisciplinasOptativas) VALUES (?, ?, ?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getNome());
			st.setDouble(2, obj.getCreditos());
			st.setInt(3, obj.getQntdDisciplinasObrigatorias());
			st.setInt(4, obj.getQntdDisciplinasOptativas());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					obj.setId(rs.getInt("IdCurso"));
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
	public void update(Curso obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE tbcursos SET Nome = ? ,  Creditos = ?, qntdDisciplinasObrigatorias = ?, qntdDisciplinasOptativas = ? WHERE IdCurso = ?");
			
			st.setString(1, obj.getNome());
			st.setDouble(2, obj.getCreditos());
			st.setInt(3, obj.getQntdDisciplinasObrigatorias());
			st.setInt(4, obj.getQntdDisciplinasOptativas());
			st.setInt(5, obj.getId());

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
			st = conn.prepareStatement("DELETE FROM tbcursos WHERE IdCurso = ?");

			st.setInt(1, id);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Curso findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM department WHERE Id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Curso curso = instantiateCurso(rs);
				return curso;
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
	public List<Curso> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM tbcursos");
			rs = st.executeQuery();
			List<Curso> list = new ArrayList<>();
			while (rs.next()) {
				Curso curso = instantiateCurso(rs);
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

	
	/*METODOS DE INSTANCIAÇÃO
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	private Curso instantiateCurso(ResultSet rs) throws SQLException {
		Curso curso = new Curso();
		curso.setId(rs.getInt("IdCurso"));
		curso.setNome(rs.getString("Nome"));
		curso.setCreditos(rs.getDouble("Creditos"));
		curso.setQntdDisciplinasObrigatorias(rs.getInt("qntdDisciplinasObrigatorias"));
		curso.setQntdDisciplinasOptativas(rs.getInt("qntdDisciplinasOptativas"));
		return curso;
	}
	
}
