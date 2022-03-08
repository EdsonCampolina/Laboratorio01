package Atores;

import Ensino.Curso;

public class Aluno extends Usuario {

	public void cadastrar(String nome, String senha) {
		this.nome = nome;
		this.senha = senha;
		this.id = ++geradorID;
	}
	
	public void matricular(Curso curso) {

	}

	public void recuperarSenha(String novaSenha) {

	}
}
