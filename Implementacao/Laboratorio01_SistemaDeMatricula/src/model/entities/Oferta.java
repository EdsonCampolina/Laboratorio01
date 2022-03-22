package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.entities.enums.Turno;

public class Oferta implements Serializable {

	private static final long serialVersionUID = 1L;

	private Professor professor;
	private List<Aluno> alunos = new ArrayList<>();
	private Disciplina disciplina;
	private Turno turno;
	private boolean isMatriculasAbertas = true;
	private boolean isOfertaCancelada = false;
	private static final int MIN_ALUNOS = 3;
	private static final int MAX_ALUNOS = 60;
	private int anoOferta;
	private int semestreOferta;
	private int Id;

	public Oferta() {

	}

	public Oferta(Disciplina disciplina, Professor professor, Turno turno, int anoOferta, int semestreOferta) {
		this.disciplina = disciplina;
		this.professor = professor;
		this.turno = turno;
		this.anoOferta = anoOferta;
		this.semestreOferta = semestreOferta;
	}

	public Oferta(Disciplina disciplina, Professor professor, Turno turno, int anoOferta, int semestreOferta,
			boolean isMatriculasAberta, boolean isOfertaCancelada, int Id) {
		this.disciplina = disciplina;
		this.professor = professor;
		this.turno = turno;
		this.anoOferta = anoOferta;
		this.semestreOferta = semestreOferta;
		this.isMatriculasAbertas = isMatriculasAberta;
		this.isOfertaCancelada = isOfertaCancelada;
		this.Id = Id;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public void fecharMatriculas() {
		if (alunos.size() < MIN_ALUNOS) {
			this.isOfertaCancelada = true;
		}
		this.isMatriculasAbertas = false;
	}

	public String adicionarAluno(Aluno aluno) {
		if (this.isMatriculasAbertas == false) {
			return "Não é possível adicionar alunos, matrículas já estão fechadas!";
		}
		if (this.alunos.size() < MAX_ALUNOS) {
			this.alunos.add(aluno);
			return "Aluno adicionado com sucesso!";
		}
		return "Aluno não foi inserido. Essa oferta já possui o número máximo de alunos!";
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public boolean isMatriculasAbertas() {
		return isMatriculasAbertas;
	}

	public boolean isOfertaCancelada() {
		return isOfertaCancelada;
	}

	public void setOfertaCancelada(boolean isOfertaCancelada) {
		this.isOfertaCancelada = isOfertaCancelada;
	}

	public int getAnoOferta() {
		return anoOferta;
	}

	public void setAnoOferta(int anoOferta) {
		this.anoOferta = anoOferta;
	}

	public int getSemestreOferta() {
		return semestreOferta;
	}

	public void setSemestreOferta(int semestreOferta) {
		this.semestreOferta = semestreOferta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(alunos, anoOferta, disciplina, isMatriculasAbertas, isOfertaCancelada, professor,
				semestreOferta, turno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Oferta other = (Oferta) obj;
		return Objects.equals(alunos, other.alunos) && anoOferta == other.anoOferta
				&& Objects.equals(disciplina, other.disciplina) && isMatriculasAbertas == other.isMatriculasAbertas
				&& isOfertaCancelada == other.isOfertaCancelada && Objects.equals(professor, other.professor)
				&& semestreOferta == other.semestreOferta && turno == other.turno;
	}

}
