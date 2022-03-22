package model.entities;

import model.dao.DaoFactory;
import model.dao.ProfessorDao;

public class Professor extends Usuario {

	private static final long serialVersionUID = 1L;

	public Professor() {

	}

	public Professor(String nome, String senha) {
		super(nome, senha);
	}

	public Professor(String nome, String senha, int Id) {
		super(nome, senha, Id);
	}

	public Professor(String senha, int Id) {
		super(senha, Id);
	}
	
	public void consultarAlunos() {
		ProfessorDao professorDao = DaoFactory.createProfessorDao();
		professorDao.consultaAlunos(this);
	}

}
