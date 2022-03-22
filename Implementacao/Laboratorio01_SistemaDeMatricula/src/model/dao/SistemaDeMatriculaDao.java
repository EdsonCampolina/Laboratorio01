package model.dao;

import model.entities.Aluno;
import model.entities.Professor;
import model.entities.Secretaria;

public interface SistemaDeMatriculaDao {


	Aluno Login(Aluno obj);

	Professor Login(Professor obj);

	Secretaria Login(Secretaria obj);

	void recuperarSenha(Aluno obj);

	void recuperarSenha(Professor obj);

	void recuperarSenha(Secretaria obj);
}
