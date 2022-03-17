package Ensino;

import java.util.ArrayList;
import java.util.List;

import Atores.Aluno;
import Atores.Professor;

public class Disciplina {

	private String nome;
	private Professor professor;
	private List<Aluno> alunos = new ArrayList<>();

	public List<Aluno> getAlunos() {
		return alunos;
	}

	private boolean matriculasAberta = true;
	private int qntdAlunosMatriculados;

	public Disciplina(String nome, Professor professor) {
		this.nome = nome;
		this.professor = professor;
	}

	public void cancelarDisciplina() {

	}

	public void cancelarNovasMatriculas() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public boolean isMatriculasAberta() {
		return matriculasAberta;
	}

	public void setMatriculasAberta(boolean matriculasAberta) {
		this.matriculasAberta = matriculasAberta;
	}

	public int getAlunosMatriculados() {
		return qntdAlunosMatriculados;
	}

	public void setAlunosMatriculados(int alunosMatriculados) {
		this.qntdAlunosMatriculados = alunosMatriculados;
	}

}
