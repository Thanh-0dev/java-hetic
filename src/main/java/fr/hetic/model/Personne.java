package fr.hetic.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class Personne extends EtreHumain {
	
	public Personne(String civilite, String prenom, String nom, LocalDate dateNaissance) {
		super(prenom, dateNaissance);
		this.civilite = civilite;
		this.nom = nom;
	}

	private String nom;
	private String civilite;
	private Adresse adresse;
	private List<Animal> animaux = new ArrayList();
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCivilite() {
		return civilite;
	}

	public void setCivilite(String civilite) {
		this.civilite = civilite;
	}

	public List<Animal> getAnimaux() {
		return animaux;
	}

	public void setAnimaux(List<Animal> animaux) {
		this.animaux = animaux;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	private String prettyCivilite() {
		if (Civilites.MADAME.equals(this.civilite)) {
			return "Mme";
		} else if (Civilites.MONSIEUR.equals(this.civilite)) {
			return "M.";
		}
		return "?";
	}

	@Override
	public String toString() {
		return "Personne : " + prettyCivilite() + " [prenom=" + prenom + ", nom=" + nom + "]";
	}
	
}
