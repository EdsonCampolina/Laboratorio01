package Atores;

public abstract class Usuario {

	protected String nome;
	protected int id;
	protected static int geradorID = 0;
	protected String senha;

	public void cadastrar(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
		this.id = ++geradorID;
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

	public void setId(int id) {
		this.id = id;
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
