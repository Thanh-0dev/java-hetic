package fr.hetic.model;

public class Adresse {

	private String numeroVoie;
	private String nomVoie;
	private Integer codePostal;
	private String ville;

	public Adresse(String numeroVoie, String nomVoie, Integer codePostal, String ville) {
		super();
		this.numeroVoie = numeroVoie;
		this.nomVoie = nomVoie;
		this.codePostal = codePostal;
		this.ville = ville;
	}

	public String getNumeroVoie() {
		return numeroVoie;
	}

	public void setNumeroVoie(String numeroVoie) {
		this.numeroVoie = numeroVoie;
	}

	public Integer getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(Integer codePostal) {
		this.codePostal = codePostal;
	}

	public String getNomVoie() {
		return nomVoie;
	}

	public void setNomVoie(String nomVoie) {
		this.nomVoie = nomVoie;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	@Override
	public String toString() {
		return "Adresse [numeroVoie=" + numeroVoie + ", nomVoie=" + nomVoie + ", codePostal=" + codePostal + ", ville="
				+ ville + "]";
	}
}
