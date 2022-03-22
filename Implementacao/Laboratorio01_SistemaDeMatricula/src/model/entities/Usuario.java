package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable {

	protected static final long serialVersionUID = 1L;
	protected String nome;
	protected int Id;
	protected String senha;

	public Usuario() {

	}

	public Usuario(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
	}

	public Usuario(String nome, String senha, int Id) {
		this.nome = nome;
		this.senha = senha;
		this.Id = Id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Id, nome, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Id == other.Id && Objects.equals(nome, other.nome) && Objects.equals(senha, other.senha);
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", Id=" + Id + ", senha=" + senha + "]";
	}

}
