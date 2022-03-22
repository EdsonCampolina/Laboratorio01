package model.entities.enums;

public enum Turno {
	MANHA("MANHA"), TARDE("TARDE"), NOITE("NOITE");

	public String descTurno;

	Turno(String value) {
		descTurno = value;
	}

	public String getTurno() {
		return this.descTurno;
	}
}
