package dev.domain.dto;

import java.time.LocalDate;

public class DtoAbsenceResponseBis {
	long idAbsence;
	LocalDate datePremierJourAbsence;
	LocalDate dateDernierJourAbsence;
	String typeConge;
	String nomDemandeur;
	String prenomDemandeur;
	
	public DtoAbsenceResponseBis(long idAbsence, LocalDate datePremierJourAbsence, LocalDate dateDernierJourAbsence,
			String typeConge, String nomDemandeur, String prenomDemandeur) {
		this.idAbsence = idAbsence;
		this.datePremierJourAbsence = datePremierJourAbsence;
		this.dateDernierJourAbsence = dateDernierJourAbsence;
		this.typeConge = typeConge;
		this.nomDemandeur = nomDemandeur;
		this.prenomDemandeur = prenomDemandeur;
	}

	public long getIdAbsence() {
		return idAbsence;
	}

	public void setIdAbsence(long idAbsence) {
		this.idAbsence = idAbsence;
	}

	public LocalDate getDatePremierJourAbsence() {
		return datePremierJourAbsence;
	}

	public void setDatePremierJourAbsence(LocalDate datePremierJourAbsence) {
		this.datePremierJourAbsence = datePremierJourAbsence;
	}

	public LocalDate getDateDernierJourAbsence() {
		return dateDernierJourAbsence;
	}

	public void setDateDernierJourAbsence(LocalDate dateDernierJourAbsence) {
		this.dateDernierJourAbsence = dateDernierJourAbsence;
	}

	public String getTypeConge() {
		return typeConge;
	}

	public void setTypeConge(String typeConge) {
		this.typeConge = typeConge;
	}

	public String getNomDemandeur() {
		return nomDemandeur;
	}

	public void setNomDemandeur(String nomDemandeur) {
		this.nomDemandeur = nomDemandeur;
	}

	public String getPrenomDemandeur() {
		return prenomDemandeur;
	}

	public void setPrenomDemandeur(String prenomDemandeur) {
		this.prenomDemandeur = prenomDemandeur;
	}
}
