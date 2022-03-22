package model.entities;

public class Aluno extends Usuario {

	private static final long serialVersionUID = 1L;
	private int IdMatricula;

	public Aluno() {

	}

	public Aluno(String nome, String senha) {
		super(nome, senha);
	}

	public Aluno(String nome, String senha, int Id) {
		super(nome, senha, Id);
	}

	public Aluno(String nome, String senha, int Id, int IdMatricula) {
		super(nome, senha, Id);
		this.IdMatricula = IdMatricula;
	}

	public int getIdMatricula() {
		return IdMatricula;
	}

	public void setIdMatricula(int idMatricula) {
		IdMatricula = idMatricula;
	}

	

	

	
}
