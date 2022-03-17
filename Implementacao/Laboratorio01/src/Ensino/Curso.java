package Ensino;

import java.util.ArrayList;
import java.util.List;

public class Curso {

	private String nome;
	private double creditos;
	private List<Disciplina> disciplinas = new ArrayList<>();
	private final static int MAX_DISCIPLINAS = 4;
	private final static int MAX_DISCIPLINAS_OPTATIVAS = 1;
	private final static int MAX_DISCIPLINAS_OBRIGATORIAS = 2;

	public Curso(String nome, double creditos) {
		this.nome = nome;
		this.creditos = creditos;
	}

	public void atribuirCreditos(double creditos) {
		this.creditos = creditos;
	}

	public void adicionarDisciplina(Disciplina disciplina) {

	}

	public void removerDisciplina(Disciplina disciplina) {

	}

}
