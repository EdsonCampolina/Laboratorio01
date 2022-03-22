package application;

import java.util.List;

import model.dao.CursoDao;
import model.dao.DaoFactory;
import model.entities.Curso;

public class Program {

	public static void main(String[] args) {
		
		
		CursoDao cursoDao = DaoFactory.createCursoDao();
		List<Curso> curso = cursoDao.findAll();
		for(Curso item : curso) {
			System.out.println(item.getNome());
		}
		
				
				
	}

}
