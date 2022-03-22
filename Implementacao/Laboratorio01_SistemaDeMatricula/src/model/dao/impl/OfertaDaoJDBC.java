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
import model.dao.OfertaDao;
import model.entities.Aluno;
import model.entities.Disciplina;
import model.entities.Oferta;
import model.entities.Professor;
import model.entities.enums.Turno;

public class OfertaDaoJDBC implements OfertaDao {

	private Connection conn;

	public OfertaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Oferta obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO tbofertas (IdProfessor,IdDisciplina,Turno,AnoOferta,SemestreOferta,MatriculasAbertas,OfertaCancelada)"
							+ " VALUES (?, ?, ?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, obj.getProfessor().getId());
			st.setInt(2, obj.getDisciplina().getId());
			st.setString(3, obj.getTurno().getTurno());
			st.setInt(4, obj.getAnoOferta());
			st.setInt(5, obj.getSemestreOferta());
			st.setBoolean(6, obj.isMatriculasAbertas());
			st.setBoolean(7, obj.isOfertaCancelada());

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
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(Oferta obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE tbofertas SET IdProfessor = ? ,  IdDisciplina = ?, Turno = ?, AnoOferta = ?, SemestreOferta = ? , MatriculasAbertas = ? , "
							+ "OfertaCancelada = ? WHERE IdCurso = ?");

			st.setInt(1, obj.getProfessor().getId());
			st.setInt(2, obj.getDisciplina().getId());
			st.setString(3, obj.getTurno().getTurno());
			st.setInt(4, obj.getAnoOferta());
			st.setInt(5, obj.getSemestreOferta());
			st.setBoolean(6, obj.isMatriculasAbertas());
			st.setBoolean(7, obj.isOfertaCancelada());
			st.setInt(8, obj.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

//	@Override
//	public void deleteById(Integer id) {
//		// TODO Auto-generated method stub
//
//	}

	@Override
	public Oferta findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT tof.*,ta.*,tp.Nome as ProfNome,tp.Senha as ProfSenha, td.Nome as NomeDisciplina, td.Obrigatoria FROM tbofertas tof "
							+ "INNER JOIN tbmatriculasofertas tmo on tmo.IdOferta = tof.IdOferta "
							+ "INNER JOIN tbmatriculas tm on tm.IdMatricula = tmo.IdMatricula "
							+ "INNER JOIN tbalunos ta on ta.IdMatricula = tm.IdMatricula "
							+ "INNER JOIN tbprofessores tp on tp.IdProfessor = tof.IdProfessor "
							+ "INNER JOIN tbdisciplinas td on td.IdDisciplina = tof.IdDisciplina "
							+ "WHERE tof.IdOferta = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			List<Aluno> list = new ArrayList<>();
			Oferta oferta = new Oferta();
			Professor professor = new Professor();
			Disciplina disciplina = new Disciplina();
			Map<Integer, Oferta> mapOferta = new HashMap<>();
			Map<Integer, Disciplina> mapDisciplina = new HashMap<>();
			Map<Integer, Professor> mapProfessor = new HashMap<>();
			while (rs.next()) {
				oferta = mapOferta.get(rs.getInt("IdOferta"));
				disciplina = mapDisciplina.get(rs.getInt("IdDisciplina"));
				professor = mapProfessor.get(rs.getInt("IdProfessor"));
				if (oferta == null) {
					oferta = instantiateOferta(rs);
					mapOferta.put(rs.getInt("IdOferta"), oferta);
				}
				if (disciplina == null) {
					disciplina = instantiateDisciplina(rs);
					mapDisciplina.put(rs.getInt("IdDisciplina"), disciplina);
				}
				if (professor == null) {
					professor = instantiateProfessor(rs);
					mapProfessor.put(rs.getInt("IdProfessor"), professor);
				}
				Aluno obj = instantiateAluno(rs);
				list.add(obj);
			}

			oferta.setAlunos(list);
			oferta.setProfessor(professor);
			oferta.setDisciplina(disciplina);
			return oferta;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Oferta> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT IdOferta FROM tbofertas");
			rs = st.executeQuery();
			List<Integer> IDs = new ArrayList<>();
			List<Oferta> ofertas = new ArrayList<>();
			while (rs.next()) {
				IDs.add(rs.getInt("IdOferta"));
			}
			for (Integer item : IDs) {
				ofertas.add(findById(item));
			}

			return ofertas;
		} catch (

		SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public void insertAluno(Integer idOferta, Integer idMatricula) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO tbmatriculasofertas (IdMatricula,IdOferta)" + " VALUES (?, ?)");
			st.setInt(1, idMatricula);
			st.setInt(2, idOferta);

			int rowsAffected = st.executeUpdate();

			if (rowsAffected == 0) {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void removeAluno(Integer idOferta, Integer idMatricula) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM tbmatriculasofertas WHERE IdMatricula = ? and IdOferta = ?)");
			st.setInt(1, idMatricula);
			st.setInt(2, idOferta);

			int rowsAffected = st.executeUpdate();

			if (rowsAffected == 0) {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
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

	private Professor instantiateProfessor(ResultSet rs) throws SQLException {
		Professor obj = new Professor();
		obj.setId(rs.getInt("IdProfessor"));
		obj.setNome(rs.getString("ProfNome"));
		obj.setSenha(rs.getString("ProfSenha"));
		return obj;
	}

	private Disciplina instantiateDisciplina(ResultSet rs) throws SQLException {
		Disciplina obj = new Disciplina();
		obj.setId(rs.getInt("IdDisciplina"));
		obj.setNome(rs.getString("NomeDisciplina"));
		obj.setObrigatoria(rs.getBoolean("Obrigatoria"));

		return obj;
	}

}
