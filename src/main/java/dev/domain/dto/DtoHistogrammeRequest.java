package dev.domain.dto;

public class DtoHistogrammeRequest {
	String departement;
	Integer mois;
	String année;
	
	public DtoHistogrammeRequest(String departement, Integer mois, String année) {
		this.departement = departement;
		this.mois = mois;
		this.année = année;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public Integer getMois() {
		return mois;
	}

	public void setMois(Integer mois) {
		this.mois = mois;
	}

	public String getAnnée() {
		return année;
	}

	public void setAnnée(String année) {
		this.année = année;
	}
}
