package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.MatriculaDao;
import model.entities.Curso;
import model.entities.Matricula;
import model.entities.Oferta;
import model.entities.enums.Turno;

public class MatriculaDaoJDBC implements MatriculaDao {

	private Connection conn;

	public MatriculaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Matricula obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO tbmatriculas (IdCurso,Ativa,SemestreMatricula,AnoMatricula) VALUES ( ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, obj.getCurso().getId());
			st.setBoolean(2, obj.isAtiva());
			st.setInt(3, obj.getSemestreMatricula());
			st.setInt(4, obj.getAnoMatricula());

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
	public void update(Matricula obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE tbmatriculas SET IdCurso = ? , Ativa = ?, SemestreMatricula = ?, AnoMatricula = ? WHERE IdMatricula = ?");

			st.setInt(1, obj.getCurso().getId());
			st.setBoolean(2, obj.isAtiva());
			st.setInt(3, obj.getSemestreMatricula());
			st.setInt(4, obj.getAnoMatricula());
			st.setInt(5, obj.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

//	@Override
//	public void deleteById(Integer id) {
//		PreparedStatement st = null;
//		try {
//			st = conn.prepareStatement("DELETE FROM tbmatriculas WHERE IdDisciplina = ?");
//
//			st.setInt(1, id);
//
//			st.executeUpdate();
//
//		} catch (SQLException e) {
//			throw new DbException(e.getMessage());
//		} finally {
//			DB.closeStatement(st);
//		}
//		
//	}

	@Override
	public Matricula findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT tm.*,tof.*,tc.Nome,tc.Creditos,tc.qntdDisciplinasObrigatorias,tc.qntdDisciplinasOptativas FROM tbmatriculas tm\r\n"
							+ "inner join tbmatriculasofertas tmo on tmo.IdMatricula = tm.IdMatricula\r\n"
							+ "inner join tbofertas tof on tof.IdOferta = tmo.IdOferta\r\n"
							+ "inner join tbcursos tc on tc.IdCurso = tm.IdCurso\r\n" + "where tm.IdMatricula = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			List<Oferta> list = new ArrayList<>();
			Matricula matricula = new Matricula();
			Curso curso = new Curso();
			Map<Integer, Matricula> map = new HashMap<>();
			Map<Integer, Curso> mapCurso = new HashMap<>();
			while (rs.next()) {
				matricula = map.get(rs.getInt("IdMatricula"));

				if (curso == null) {
					curso = instantiateCurso(rs);
					mapCurso.put(rs.getInt("IdCruso"), curso);
				}
				if (matricula == null) {
					matricula = instantiateMatricula(rs, curso);
					map.put(rs.getInt("IdMatricula"), matricula);
				}

				Oferta obj = instantiateOferta(rs);
				list.add(obj);
			}

			matricula.setOfertas(list);
			return matricula;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

//	@Override
//	public List<Matricula> findAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void insertMatriculaOferta(Matricula obj, Oferta oferta) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO tbmatriculasofertas (IdMatricula,IdOferta,) VALUES ( ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setInt(1, obj.getId());
			st.setInt(2, oferta.getId());
			int rowsAffected = st.executeUpdate();

			if (rowsAffected == 0) {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}

		catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	/*
	 * METODOS INSTANCIAÇÃO
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

	private Matricula instantiateMatricula(ResultSet rs, Curso curso) throws SQLException {
		Matricula obj = new Matricula();
		obj.setId(rs.getInt("IdMatricula"));
		obj.setAnoMatricula(rs.getInt("AnoMatricula"));
		obj.setSemestreMatricula(rs.getInt("SemestreMatricula"));
		obj.setAtiva(rs.getBoolean("Ativa"));
		obj.setCurso(curso);
		return obj;
	}

	private Oferta instantiateOferta(ResultSet rs) throws SQLException {

		Oferta obj = new Oferta();
		obj.setId(rs.getInt("IdOferta"));
		obj.setTurno(Turno.valueOf(rs.getString("Turno")));
		obj.setAnoOferta(rs.getInt("AnoOferta"));
		obj.setSemestreOferta(rs.getInt("SemestreOferta"));
		obj.setMatriculasAbertas(rs.getBoolean("MatriculasAbertas"));
		obj.setOfertaCancelada(rs.getBoolean("OfertaCancelada"));
		return obj;

	}

}
