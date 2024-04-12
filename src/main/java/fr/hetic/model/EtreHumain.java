package fr.hetic.model;

import java.time.LocalDate;

public abstract class EtreHumain {
	protected String prenom;
	protected LocalDate dateNaissance;
	
	public EtreHumain(String prenom, LocalDate dateNaissance) {
		super();
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
}
