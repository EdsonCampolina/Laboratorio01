package Atores;

import java.util.ArrayList;
import java.util.List;

import Ensino.Disciplina;

public class Professor extends Usuario {

	List<Disciplina> disciplinas = new ArrayList<>();

	public Professor(String nome, String senha, char tipo) {
		super(nome, senha, tipo);
	}

	public void cadastrar(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
		this.id = ++geradorID;
	}

	public List<Aluno> consultarAlunosPorDisciplina(Disciplina disciplina) {
		List<Aluno> alunos = new ArrayList<>();
		for (Disciplina discipline : disciplinas) {
			alunos.addAll(discipline.getAlunos());
		}
		return alunos;
	}

	public void adicionarDisciplina(Disciplina disciplina) {
		disciplinas.add(disciplina);
	}
}
