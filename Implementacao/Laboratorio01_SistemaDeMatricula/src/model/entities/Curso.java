package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Curso implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private double creditos;
	private List<Disciplina> disciplinas = new ArrayList<>();
	private int qntdDisciplinasObrigatorias = 0;
	private int qntdDisciplinasOptativas = 0;
	private int Id;
	private static final int MAX_DISCIPLINAS = 4;
	private static final int MAX_DISCIPLINAS_OPTATIVAS = 1;
	private static final int MAX_DISCIPLINAS_OBRIGATORIAS = 2;

	public Curso() {

	}

	public Curso(String nome, double creditos) {
		this.nome = nome;
		this.creditos = creditos;
	}

	public Curso(String nome, double creditos, List<Disciplina> disciplinas, int qntdDisciplinasObrigatorias,
			int qntdDisciplinasOptativas, int Id) {
		this.nome = nome;
		this.creditos = creditos;
		this.disciplinas = disciplinas;
		this.qntdDisciplinasObrigatorias = qntdDisciplinasObrigatorias;
		this.qntdDisciplinasOptativas = qntdDisciplinasOptativas;
		this.Id = Id;
	}

	public String adicionarDisciplinaObrigatoria(Disciplina disciplina) {
		if (disciplinas.size() < MAX_DISCIPLINAS && qntdDisciplinasObrigatorias < MAX_DISCIPLINAS_OBRIGATORIAS) {
			disciplinas.add(disciplina);
			qntdDisciplinasObrigatorias++;
			return "Disciplina adicionada com sucesso!";
		}
		return "Não foi possível adicionar disciplina, esse curso já possui o número máximo de disciplinas!";
	}

	public String adicionarDisciplinaOptativa(Disciplina disciplina) {
		if (disciplinas.size() < MAX_DISCIPLINAS && qntdDisciplinasOptativas < MAX_DISCIPLINAS_OPTATIVAS) {
			disciplinas.add(disciplina);
			qntdDisciplinasOptativas++;
			return "Disciplina adicionada com sucesso!";
		}
		return "Não foi possível adicionar disciplina, esse curso já possui o número máximo de disciplinas optativas!";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getCreditos() {
		return creditos;
	}

	public void setCreditos(double creditos) {
		this.creditos = creditos;
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id, creditos, disciplinas, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curso other = (Curso) obj;
		return Id == other.Id && Double.doubleToLongBits(creditos) == Double.doubleToLongBits(other.creditos)
				&& Objects.equals(disciplinas, other.disciplinas) && Objects.equals(nome, other.nome);
	}

}
