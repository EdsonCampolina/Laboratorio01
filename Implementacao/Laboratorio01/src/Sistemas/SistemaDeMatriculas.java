package Sistemas;

import java.util.ArrayList;
import java.util.List;

import Atores.Professor;
import Atores.Usuario;
import Ensino.Curso;
import Ensino.Disciplina;

public class SistemaDeMatriculas {

	private List<Usuario> usuarios = new ArrayList<>();
	private List<Disciplina> disciplinas = new ArrayList<>();
	private List<Curso> cursos = new ArrayList<>();

	public void notificarMatriculaAluno(SistemaDeCobranca sdc) {
		sdc.notificar();
	}

	public Usuario fazerLogin(int login, String senha) {
		for (Usuario user : usuarios) {
			if (user.getId() == login && user.getSenha().equals(senha)) {
				System.out.println("Login realizado com sucesso!");
				System.out.println("Bem-vindo " + user.getNome() + "!");
				return user;
			}
		}
		System.out.println("Falha ao realizar login!");
		return null;
	}

	public void recuperarSenha(int ID, String novaSenha) {
		boolean recuperar = false;
		for (Usuario user : usuarios) {
			if (user.getId() == ID) {
				user.setSenha(novaSenha);
				System.out.println("Senha alterada com sucesso!");
			}
		}
		if (!recuperar) {
			System.out.println("Esse login não existe!");
		}
	}

	public void cadastrarDisciplina(Disciplina disciplina) {
		disciplinas.add(disciplina);
		disciplina.getProfessor().adicionarDisciplina(disciplina);
	}

	public void cadastrarCurso(Curso curso) {
		cursos.add(curso);
	}

	public void cadastrarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}

	public Professor retornaProfessor(int login) {
		for (Usuario user : usuarios) {
			if (user.getId() == login && user instanceof Professor) {
				return (Professor) user;
			}
		}
		return null;
	}

	public Disciplina retornaDisciplina(String nome) {
		for (Disciplina discipline : disciplinas) {
			if (discipline.getNome().equals(nome)) {
				return discipline;
			}
		}
		return null;
	}
}
