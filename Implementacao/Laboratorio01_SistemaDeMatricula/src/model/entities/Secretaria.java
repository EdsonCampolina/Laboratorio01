package model.entities;

public class Secretaria extends Usuario {

	private static final long serialVersionUID = 1L;

	public Secretaria() {

	}

	public Secretaria(String nome, String senha) {
		super(nome, senha);
	}

	public Secretaria(String nome, String senha, int Id) {
		super(nome, senha, Id);
	}

	public Secretaria(String senha, int Id) {
		super( senha, Id);
	}
}
