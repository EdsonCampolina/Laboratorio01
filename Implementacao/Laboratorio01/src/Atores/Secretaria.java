package Atores;

import Ensino.Disciplina;

public class Secretaria extends Usuario {

	public void cadastrar(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
		this.id = ++geradorID;
	}

	public void gerarCurriculoProfessor(Professor professor) {
	}

	public void gerarCurriculoAluno(Aluno aluno) {
	}

	public void gerarCurriculoDisciplina(Disciplina disciplina) {
	}
}
