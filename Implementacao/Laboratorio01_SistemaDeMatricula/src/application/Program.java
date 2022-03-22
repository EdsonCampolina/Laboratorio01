package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.OfertaDao;
import model.entities.Oferta;

public class Program {

	public static void main(String[] args) {
		
		
//		CursoDao cursoDao = DaoFactory.createCursoDao();
//		List<Curso> curso = cursoDao.findAll();
//		for(Curso item : curso) {
//			System.out.println(item.getNome());
//		}
		
//		DisciplinaDao disciplinaDao = DaoFactory.createDisciplinaDao();
//		disciplinaDao.findAll();
//		List<Disciplina> disciplinas = disciplinaDao.findAll();
//		for(Disciplina item : disciplinas) {
//			System.out.println(item.getNome());
//		}
		
//		Disciplina disciplina = new Disciplina();
//		Professor professor = new Professor();
//		Oferta of = new Oferta( disciplina,  professor, Turno.valueOf("MANHA"), 2022, 1); 
//		System.out.println(of.getTurno());
		
		OfertaDao ofertaDao = DaoFactory.createOfertaDao();
//		Oferta oferta = ofertaDao.findById(1);
//		System.out.println(oferta.getDisciplina().getNome());
		
		
		List<Oferta> teste = ofertaDao.findAll();
		for(Oferta item : teste) {
			System.out.println(item.getDisciplina().getNome());
		}
				
				
	}

}
