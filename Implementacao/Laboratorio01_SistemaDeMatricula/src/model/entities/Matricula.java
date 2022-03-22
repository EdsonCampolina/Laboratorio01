package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.dao.DaoFactory;
import model.dao.MatriculaDao;

public class Matricula implements Serializable {

	private static final long serialVersionUID = 1L;

	private Curso curso;
	private int Id;
	private List<Oferta> ofertas = new ArrayList<>();
	private boolean isAtiva = true;
	private int anoMatricula;
	private int semestreMatricula;

	public Matricula() {

	}

	public Matricula(Curso curso, int anoMatricula, int semestreMatricula) {
		this.curso = curso;
		this.anoMatricula = anoMatricula;
		this.semestreMatricula = semestreMatricula;
	}

	public Matricula(Curso curso, int anoMatricula, int semestreMatricula, boolean isAtiva, int Id,
			List<Oferta> ofertas) {
		this.curso = curso;
		this.anoMatricula = anoMatricula;
		this.semestreMatricula = semestreMatricula;
		this.isAtiva = isAtiva;
		this.Id = Id;
		this.ofertas = ofertas;
	}

	public void cancelarMatricula() {
		this.isAtiva = false;
		MatriculaDao matriculaDao = DaoFactory.createMatriculaDao();
		matriculaDao.update(this);
		System.out.println("Matrícula cancelada!");
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	public boolean isAtiva() {
		return isAtiva;
	}

	public void setAtiva(boolean isAtiva) {
		this.isAtiva = isAtiva;
	}

	public int getAnoMatricula() {
		return anoMatricula;
	}

	public void setAnoMatricula(int anoMatricula) {
		this.anoMatricula = anoMatricula;
	}

	public int getSemestreMatricula() {
		return semestreMatricula;
	}

	public void setSemestreMatricula(int semestreMatricula) {
		this.semestreMatricula = semestreMatricula;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id, anoMatricula, curso, isAtiva, ofertas, semestreMatricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matricula other = (Matricula) obj;
		return Id == other.Id && anoMatricula == other.anoMatricula && Objects.equals(curso, other.curso)
				&& isAtiva == other.isAtiva && Objects.equals(ofertas, other.ofertas)
				&& semestreMatricula == other.semestreMatricula;
	}

}
