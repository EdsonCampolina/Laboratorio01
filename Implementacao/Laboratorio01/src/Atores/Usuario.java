package Atores;

public abstract class Usuario {

	protected String nome;
	protected int id;
	protected static int geradorID = 0;
	protected String senha;
	protected char tipo;

	public Usuario(String nome, String senha, char tipo) {
		this.nome = nome;
		this.id = ++geradorID;
		this.senha = senha;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return id;
	}

	public static int getGeradorID() {
		return geradorID;
	}

	public static void setGeradorID(int geradorID) {
		Usuario.geradorID = geradorID;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	

}
