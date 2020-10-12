package dev.domain.dto;

public class DtoHistogrammeRequest {
	String departement;
	String mois;
	String annee;
	
	public DtoHistogrammeRequest(String departement, String mois, String annee) {
		this.departement = departement;
		this.mois = mois;
		this.annee = annee;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getMois() {
		return mois;
	}

	public void setMois(String mois) {
		this.mois = mois;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String année) {
		this.annee = année;
	}
}
