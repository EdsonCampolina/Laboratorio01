package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Disciplina implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private boolean isObrigatoria;
	private int Id;

	public Disciplina() {

	}

	public Disciplina(String nome, boolean isObrigatoria) {
		this.nome = nome;
		this.isObrigatoria = isObrigatoria;
	}

	public Disciplina(String nome, boolean isObrigatoria, int Id) {
		this.nome = nome;
		this.isObrigatoria = isObrigatoria;
		this.Id = Id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isObrigatoria() {
		return isObrigatoria;
	}

	public void setObrigatoria(boolean isObrigatoria) {
		this.isObrigatoria = isObrigatoria;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(isObrigatoria, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disciplina other = (Disciplina) obj;
		return isObrigatoria == other.isObrigatoria && Objects.equals(nome, other.nome);
	}

}
