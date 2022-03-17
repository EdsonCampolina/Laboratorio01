package Application;

import java.util.Scanner;

import Atores.Aluno;
import Atores.Professor;
import Atores.Secretaria;
import Atores.Usuario;
import Ensino.Curso;
import Ensino.Disciplina;
import Sistemas.SistemaDeCobranca;
import Sistemas.SistemaDeMatriculas;

public class Main {

	public static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		SistemaDeMatriculas sdm = new SistemaDeMatriculas();
		SistemaDeCobranca sdc = new SistemaDeCobranca();
		Usuario userLogado = null;
		int opcao = -1;
		do {
			menu();
			opcao = sc.nextInt();
			switch (opcao) {
			case 1:
				System.out.println("A - Aluno(a)");
				System.out.println("P - Professor(a)");
				System.out.println("S - Secretário(a)");
				char cadUsuario = sc.next().charAt(0);
				sc.nextLine();

				String nome;
				String senha;
				if (cadUsuario == 'A') {
					System.out.println("Digite o nome:");
					nome = sc.nextLine();
					System.out.println("Digite a senha:");
					senha = sc.nextLine();
					Usuario novoAluno = new Aluno(nome, senha, 'A');
					sdm.cadastrarUsuario(novoAluno);
					System.out.println("Cadastro realizada com sucesso!");
					System.out.println("Login do seu usuário é " + novoAluno.getId());
				} else if (cadUsuario == 'P') {
					System.out.println("Digite o nome:");
					nome = sc.nextLine();
					System.out.println("Digite a senha:");
					senha = sc.nextLine();
					Usuario novoProfessor = new Professor(nome, senha, 'P');
					sdm.cadastrarUsuario(novoProfessor);
					System.out.println("Matrícula realizada com sucesso!");
					System.out.println("Login do seu usuário é " + novoProfessor.getId());
				} else if (cadUsuario == 'S') {
					System.out.println("Digite o nome:");
					nome = sc.nextLine();
					System.out.println("Digite a senha:");
					senha = sc.nextLine();
					Usuario novaSecretaria = new Secretaria(nome, senha, 'S');
					sdm.cadastrarUsuario(novaSecretaria);
					System.out.println("Matrícula realizada com sucesso!");
					System.out.println("Login do seu usuário é " + novaSecretaria.getId());
				}

				break;
			case 2:
				System.out.println("Digite o seu ID:");
				int login = sc.nextInt();
				System.out.println("Digite sua senha:");
				sc.nextLine();
				String senhaLogin = sc.nextLine();
				userLogado = sdm.fazerLogin(login, senhaLogin);
				if (userLogado != null) {
					if (userLogado instanceof Aluno) {
						alunoMenu();
					}
					if (userLogado instanceof Professor) {
						professorMenu();
					}
					if (userLogado instanceof Secretaria) {
						secretariaMenu();
					}
				}
				break;
			case 3:
				System.out.println("Insira o nome da disciplina");
				sc.nextLine();
				String nomeDisciplina = sc.nextLine();
				System.out.println("Insira o ID do professor responsável:");
				int idProfessorDisciplina = sc.nextInt();
				Professor professorDisciplina = sdm.retornaProfessor(idProfessorDisciplina);
				if (professorDisciplina != null) {
					sdm.cadastrarDisciplina(new Disciplina(nomeDisciplina, professorDisciplina));
				} else {
					System.out.println("Esse professor não existe, logo, não foi possível cadastrar a disciplina!");
				}
				break;
			case 4:
				System.out.println("Insira o nome do curso");
				sc.nextLine();
				String nomeCurso = sc.nextLine();
				System.out.println("Insira a quantidade de créditos:");
				double creditos = sc.nextDouble();
				Curso curso = new Curso(nomeCurso, creditos);
				char novoCurso;
				do {
					System.out.println("Inserir disciplina(S/N):");
					novoCurso = sc.next().charAt(0);
					sc.nextLine();
					if (novoCurso == 'S') {
						System.out.println("Insira o nome da disciplina:");
						sc.nextLine();
						String inserirDisciplina = sc.nextLine();
						Disciplina disciplinaInserir = sdm.retornaDisciplina(inserirDisciplina);
						if (disciplinaInserir != null) {
							curso.adicionarDisciplina(null);
						} else {
							System.out.println("Não existe uma disciplina com esse nome!");
						}

					}
				} while (novoCurso == 'S');
				break;
			case 5:
				System.out.println("Digite seu login:");
				int loginRecuperar = sc.nextInt();
				System.out.println("Digite a nova senha:");
				sc.nextLine();
				String recuperarSenha = sc.nextLine();
				sdm.recuperarSenha(loginRecuperar, recuperarSenha);
			}
		} while (opcao != 0 && userLogado == null);

		sc.close();

	}

	public static void menu() {
		System.out.println("1 - Cadastrar usuário");
		System.out.println("2 - Fazer login");
		System.out.println("3 - Cadastrar disciplina");
		System.out.println("4 - Cadastrar curso");
		System.out.println("5 - Esqueceu a senha?");
		System.out.println("0 - Sair");
	}

	public static void alunoMenu() {
		System.out.println("1 - Fazer matrícula");
		System.out.println("2 - Cancelar matrícula");
		System.out.println("0 - Sair");
	}

	public static void professorMenu() {
		System.out.println("1 - Consultar alunos por disciplina");
		System.out.println("0 - Sair");
	}

	public static void secretariaMenu() {
		System.out.println("1 - Gerar currículo das disciplinas");
		System.out.println("2 - Gerar currículo dos professores");
		System.out.println("3 - Gerar currículo dos alunos");
		System.out.println("0 - Sair");
	}

}
