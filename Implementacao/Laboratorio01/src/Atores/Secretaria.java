package Atores;

import Ensino.Disciplina;

public class Secretaria extends Usuario {

	public Secretaria(String nome, String senha, char tipo) {
		super(nome, senha, tipo);
	}

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
