package Atores;

import java.util.List;

import Ensino.Disciplina;

public class Professor extends Usuario{

	
	public void cadastrar(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
		this.id = ++geradorID;
	}
	public List<Aluno> consultarAlunosPorDisciplina(Disciplina disciplina) {
		return null;
	}
}
