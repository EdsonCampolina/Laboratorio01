package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import db.DbException;
import model.dao.SistemaDeMatriculaDao;
import model.entities.Aluno;
import model.entities.Professor;
import model.entities.Secretaria;

public class SistemaDeMatriculaDaoJDBC implements SistemaDeMatriculaDao {

	private Connection conn;

	public SistemaDeMatriculaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Aluno Login(Aluno obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM tbalunos WHERE IdAluno = ? and Senha = ?");
			st.setInt(1, obj.getId());
			st.setString(2, obj.getSenha());
			rs = st.executeQuery();
			if (rs.next()) {
				Aluno retorno = instantiateAluno(rs);
				return retorno;
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
	public Professor Login(Professor obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM tbprofessores WHERE IdProfessor = ? and Senha = ?");
			st.setInt(1, obj.getId());
			st.setString(2, obj.getSenha());
			rs = st.executeQuery();
			if (rs.next()) {
				Professor retorno = instantiateProfessor(rs);
				return retorno;
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
	public Secretaria Login(Secretaria obj) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM tbsecretaria WHERE IdSecretaria = ? and Senha = ?");
			st.setInt(1, obj.getId());
			st.setString(2, obj.getSenha());
			rs = st.executeQuery();
			if (rs.next()) {
				Secretaria retorno = instantiateSecretaria(rs);
				return retorno;
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
	public void recuperarSenha(Aluno obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE tbalunos SET Senha = ? WHERE IdAluno = ?");
			st.setString(1, obj.getSenha());
			st.setInt(2, obj.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void recuperarSenha(Professor obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE tbprofessores SET Senha = ? WHERE IdProfessor = ?");
			st.setString(1, obj.getSenha());
			st.setInt(2, obj.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void recuperarSenha(Secretaria obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE tbsecretaria SET Senha = ? WHERE IdSecretaria = ?");
			st.setString(1, obj.getSenha());
			st.setInt(2, obj.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}
	
	/*METODOS DE INSTANCIAÇÃO
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
	
	private Professor instantiateProfessor(ResultSet rs) throws SQLException {
		Professor obj = new Professor();
		obj.setId(rs.getInt("IdProfessor"));
		obj.setNome(rs.getString("Nome"));
		obj.setSenha(rs.getString("Senha"));
		return obj;
	}
	
	private Secretaria instantiateSecretaria(ResultSet rs) throws SQLException {
		Secretaria obj = new Secretaria();
		obj.setId(rs.getInt("IdSecretaria"));
		obj.setNome(rs.getString("Nome"));
		obj.setSenha(rs.getString("Senha"));
		return obj;
	}

	
	}

