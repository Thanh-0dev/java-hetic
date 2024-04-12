package fr.hetic.model;

import java.time.LocalDate;

public class Animal extends EtreHumain {

	public Animal(String prenom, String race, LocalDate dateNaissance) {
		super(prenom, dateNaissance);
		this.race = race;
	}

	private String race;

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	@Override
	public String toString() {
		return "Animal [prenom=" + prenom + ", race=" + race + "]";
	}


}
