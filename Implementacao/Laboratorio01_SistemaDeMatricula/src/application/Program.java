package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.dao.AlunoDao;
import model.dao.CursoDao;
import model.dao.DaoFactory;
import model.dao.DisciplinaDao;
import model.dao.MatriculaDao;
import model.dao.OfertaDao;
import model.dao.ProfessorDao;
import model.dao.SistemaDeMatriculaDao;
import model.entities.Aluno;
import model.entities.Curso;
import model.entities.Disciplina;
import model.entities.Matricula;
import model.entities.Oferta;
import model.entities.Professor;
import model.entities.Secretaria;
import model.entities.SistemaDeCobranca;
import model.entities.SistemaDeMatricula;
import model.entities.Usuario;

public class Program {

	public static Scanner sc = new Scanner(System.in);
	public static Usuario userLogado = null;
	public static SistemaDeMatricula sdm = new SistemaDeMatricula();
	public static SistemaDeCobranca sdc = new SistemaDeCobranca();

	public static void main(String[] args) {

		int opcao = -1;
		do {
			userLogado = null;
			System.out.println("1 - Fazer login como Secretária");
			System.out.println("2 - Fazer login como Aluno");
			System.out.println("3 - Fazer login como Professor");
			System.out.println("4 - Esqueceu a senha");
			System.out.println("0 - Encerrar programa");
			opcao = sc.nextInt();
			menu(opcao);
		} while (opcao != 0 && userLogado == null);

	}

	public static void menu(int option) {
		int Id;
		String senha;
		SistemaDeMatriculaDao sdmdj = DaoFactory.createSistemaDeMatriculaDao();
		switch (option) {
		case 1:
			System.out.println("Digite o seu Id:");
			Id = sc.nextInt();
			sc.nextLine();
			System.out.println("Digite a sua senha");
			senha = sc.nextLine();
			Secretaria secretaria = new Secretaria(senha, Id);
			userLogado = sdmdj.Login(secretaria);
			if (userLogado != null) {
				System.out.println("Bem-vindo(a) " + userLogado.getNome());
				menuSecretaria(-1);
			} else {
				System.out.println("Falha ao fazer login!");
			}
			break;
		case 2:
			System.out.println("Digite o seu Id:");
			Id = sc.nextInt();
			sc.nextLine();
			System.out.println("Digite a sua senha");
			senha = sc.nextLine();
			Aluno aluno = new Aluno(senha, Id);
			userLogado = sdmdj.Login(aluno);
			if (userLogado != null) {
				System.out.println("Bem-vindo(a) " + userLogado.getNome());
				menuAluno(-1);
			} else {
				System.out.println("Falha ao fazer login!");
			}
			break;
		case 3:
			System.out.println("Digite o seu Id:");
			Id = sc.nextInt();
			sc.nextLine();
			System.out.println("Digite a sua senha");
			senha = sc.nextLine();
			Professor professor = new Professor(senha, Id);
			userLogado = sdmdj.Login(professor);
			if (userLogado != null) {
				System.out.println("Bem-vindo(a) " + userLogado.getNome());
				menuProfessor(-1);
			} else {
				System.out.println("Falha ao fazer login!");
			}
			break;
		case 4:
			System.out.println("Digite o seu tipo de usuário:");
			System.out.println("A - Aluno");
			System.out.println("S - Secretária");
			System.out.println("P - Professor");
			char cadUsuario = sc.next().charAt(0);
			sc.nextLine();
			System.out.println("Digite o seu Id:");
			Id = sc.nextInt();
			sc.nextLine();
			System.out.println("Digite a nova senha");
			senha = sc.nextLine();
			if (cadUsuario == 'A') {
				sdmdj.recuperarSenha(new Aluno(senha, Id));
				System.out.println("Senha alterada com sucesso!");
			} else if (cadUsuario == 'S') {
				sdmdj.recuperarSenha(new Secretaria(senha, Id));
				System.out.println("Senha alterada com sucesso!");
			} else if (cadUsuario == 'P') {
				sdmdj.recuperarSenha(new Professor(senha, Id));
				System.out.println("Senha alterada com sucesso!");
			} else {
				System.out.println("Tipo de usuário inexistente!");
			}
			break;

		}
	}

	public static void menuSecretaria(int opcao) {
		do {
			System.out.println();
			System.out.println("1 - Cadastrar Professor");
			System.out.println("2 - Cadastrar Aluno");
			System.out.println("3 - Gerar currículo das disciplinas");
			System.out.println("4 - Gerar currículo das professores");
			System.out.println("5 - Gerar currículo dos alunos");
			System.out.println("0 - Encerrar programa");
			opcao = sc.nextInt();
			subMenuSecretario(opcao);
		} while (opcao != 0);
	}

	public static void menuAluno(int opcao) {
		do {
			System.out.println();
			System.out.println("1 - Cancelar matrícula");
			System.out.println("2 - Matricular em disciplina optativa");
			System.out.println("3 - Matricular em disciplina obrigatória");
			System.out.println("0 - Encerrar programa");
			opcao = sc.nextInt();
			subMenuAluno(opcao);
		} while (opcao != 0);
	}

	private static void subMenuAluno(int opcao) {
		int IdOferta;
		List<Oferta> ofertas = new ArrayList<>();
		Oferta ofertaMatricular = null;
		OfertaDao ofertasDao = DaoFactory.createOfertaDao();
		switch (opcao) {
		case 1:
			MatriculaDao matriculaDao = DaoFactory.createMatriculaDao();
			Matricula matricula = matriculaDao.findById(userLogado.getId());
			matricula.cancelarMatricula();
			break;
		case 2:
			ofertas = ofertasDao.findAll();
			for (Oferta item : ofertas) {
				if (!(item.getDisciplina().isObrigatoria()) && item.getId() != 0) {
					System.out.println("Oferta: " + item.getAnoOferta() + " / Id: " + item.getId() + " / Disciplina: "
							+ item.getDisciplina().getNome());
				}
			}
			System.out.println("Digite o ID da Oferta que você deseja se cadastrar:");
			IdOferta = sc.nextInt();
			for (Oferta item : ofertas) {
				if (item.getId() == IdOferta && !(item.getDisciplina().isObrigatoria())) {
					ofertaMatricular = item;
				}

			}
			System.out.println(ofertaMatricular.adicionarAluno((Aluno) userLogado));
			break;
		case 3:
			ofertas = ofertasDao.findAll();
			for (Oferta item : ofertas) {
				if (item.getDisciplina().isObrigatoria() && item.getId() != 0) {
					System.out.println("Oferta: " + item.getAnoOferta() + " / Id: " + item.getId() + " / Disciplina: "
							+ item.getDisciplina().getNome());
				}
			}
			System.out.println("Digite o ID da Oferta que você deseja se cadastrar:");
			IdOferta = sc.nextInt();
			for (Oferta item : ofertas) {
				if (item.getId() == IdOferta && item.getDisciplina().isObrigatoria()) {
					ofertaMatricular = item;
				}

			}
			System.out.println(ofertaMatricular.adicionarAluno((Aluno) userLogado));
			break;
		}
	}

	public static void menuProfessor(int opcao) {
		while (opcao != 0) {
			System.out.println("1 - Consultar alunos");
			System.out.println("0 - Encerrar programa");
			opcao = sc.nextInt();
			if (opcao == 1) {
				ProfessorDao professorDao = DaoFactory.createProfessorDao();
				Professor professor = professorDao.findById(userLogado.getId());
				professor.consultarAlunos();
			}
		}
	}

	public static void subMenuSecretario(int opcao) {
		AlunoDao alunoDao = DaoFactory.createAlunoDao();
		String nome;
		String senha;
		switch (opcao) {
		case 1:
			System.out.println("Digite o nome do professor:");
			sc.nextLine();
			nome = sc.nextLine();
			System.out.println("Digite a senha:");
			senha = sc.nextLine();
			ProfessorDao professorJDBC = DaoFactory.createProfessorDao();
			Professor professor = new Professor(nome, senha);
			professorJDBC.insert(professor);
			System.out.println("Professor cadastrado com sucesso!");
			System.out.println("Id do professor é " + professor.getId());
			break;
		case 2:
			System.out.println("Digite o nome do aluno:");
			sc.nextLine();
			nome = sc.nextLine();
			System.out.println("Digite a senha:");
			senha = sc.nextLine();
			System.out.println("Criando matrícula do aluno!");
			CursoDao cursoJDBC = DaoFactory.createCursoDao();
			List<Curso> cursos = cursoJDBC.findAll();
			for (Curso item : cursos) {
				System.out.println("Nome do curso: " + item.getNome() + " / Id Curso: " + item.getId());
			}
			System.out.println("Escreva o ID do curso selecionado:");
			int Id = sc.nextInt();
			System.out.println("Digite o ano do cadastro da matrícula:");
			int anoMatricula = sc.nextInt();
			System.out.println("Digite o semestre que o aluno está se matriculando:");
			int semestreMatricula = sc.nextInt();
			Curso cursoInserir = null;
			for (Curso item : cursos) {
				if (item.getId() == Id)
					cursoInserir = item;
			}
			Matricula matricula = new Matricula(cursoInserir, anoMatricula, semestreMatricula);
			MatriculaDao matriculaDao = DaoFactory.createMatriculaDao();
			matriculaDao.insert(matricula);
			Aluno aluno = new Aluno(matricula.getId(), nome, senha);

			alunoDao.insert(aluno);
			System.out.println("Aluno cadastrado com sucesso! ");
			System.out.println("ID do aluno é igual a " + aluno.getId());
			sdm.notificarSistemaDeCobranca(aluno);
			break;
		case 3:
			DisciplinaDao disciplinaDao = DaoFactory.createDisciplinaDao();
			List<Disciplina> disciplinas = disciplinaDao.findAll();
			System.out.println("Relatório das disciplinas:");
			for (Disciplina item : disciplinas) {
				System.out.println("Nome: " + item.getNome() + " / Id : " + item.getId() + " / "
						+ (item.isObrigatoria() == true ? "Obrigatória" : "Optativa"));
			}
			break;
		case 4:
			ProfessorDao professorDao = DaoFactory.createProfessorDao();
			List<Professor> professores = professorDao.findAll();
			for (Professor item : professores) {
				System.out.println("Nome: " + item.getNome() + " / Id : " + item.getId());
			}
			break;
		case 5:
			List<Aluno> alunos = alunoDao.findAll();
			for (Aluno item : alunos) {
				System.out.println("Nome: " + item.getNome() + " / Id : " + item.getId() + " / Id Matrícula : "
						+ item.getIdMatricula());
			}
			break;

		}
	}
}
