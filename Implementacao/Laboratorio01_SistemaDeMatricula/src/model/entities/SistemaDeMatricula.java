package model.entities;

public class SistemaDeMatricula {

	private static SistemaDeCobranca sdc = new SistemaDeCobranca();
	
	public void notificarSistemaDeCobranca(Aluno aluno) {
		sdc.notificarMatricula(aluno);
	}
}
