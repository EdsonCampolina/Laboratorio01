package model.dao;

import db.DB;
import model.dao.impl.AlunoDaoJDBC;
import model.dao.impl.CursoDaoJDBC;
import model.dao.impl.DisciplinaDaoJDBC;
import model.dao.impl.MatriculaDaoJDBC;
import model.dao.impl.OfertaDaoJDBC;
import model.dao.impl.ProfessorDaoJDBC;
import model.dao.impl.SecretariaDaoJDBC;
import model.dao.impl.SistemaDeMatriculaDaoJDBC;

public class DaoFactory {

	public static AlunoDao createAlunoDao() {
		return new AlunoDaoJDBC(DB.getConnection());
	}

	public static ProfessorDao createProfessorDao() {
		return new ProfessorDaoJDBC(DB.getConnection());
	}

	public static SecretariaDao createSecretariaDao() {
		return new SecretariaDaoJDBC(DB.getConnection());
	}

	public static CursoDao createCursoDao() {
		return new CursoDaoJDBC(DB.getConnection());
	}

	public static DisciplinaDao createDisciplinaDao() {
		return new DisciplinaDaoJDBC(DB.getConnection());
	}

	public static MatriculaDao createMatriculaDao() {
		return new MatriculaDaoJDBC(DB.getConnection());
	}

	public static OfertaDao createOfertaDao() {
		return new OfertaDaoJDBC(DB.getConnection());
	}

	public static SistemaDeMatriculaDao createSistemaDeMatriculaDao() {
		return new SistemaDeMatriculaDaoJDBC(DB.getConnection());
	}
}
