package model.entities;

public class Professor extends Usuario {

	private static final long serialVersionUID = 1L;

	public Professor() {
		
	}
	public Professor(String nome,String senha) {
		super(nome,senha);
	}
	public Professor(String nome, String senha, int Id) {
		super(nome,senha,Id);
	}
}
