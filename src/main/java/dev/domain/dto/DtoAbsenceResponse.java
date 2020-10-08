package dev.domain.dto;

import java.time.LocalDate;

import dev.domain.entite.Absence;

public class DtoAbsenceResponse {

	long idCollegue;
	LocalDate datePremierJourAbsence;
	LocalDate dateDernierJourAbsence;
	String typeConge;
	String commentaireAbsence;
	String statutDemande;

	public DtoAbsenceResponse(Absence abs) {
		this.idCollegue = abs.getCollegue().getId();
		this.datePremierJourAbsence = abs.getDatePremierJourAbsence();
		this.dateDernierJourAbsence = abs.getDateDernierJourAbsence();
		// this.typeConge = abs.getTypeConge().toString();
		this.typeConge = "test type";
		this.commentaireAbsence = abs.getCommentaireAbsence();
		this.statutDemande = abs.getStatutDemandeAbsence().toString();
	}

	public long getIdCollegue() {
		return idCollegue;
	}

	public void setIdCollegue(long idCollegue) {
		this.idCollegue = idCollegue;
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

	public String getCommentaireAbsence() {
		return commentaireAbsence;
	}

	public void setCommentaireAbsence(String commentaireAbsence) {
		this.commentaireAbsence = commentaireAbsence;
	}

	public String getStatutDemande() {
		return statutDemande;
	}

	public void setStatutDemande(String statutDemande) {
		this.statutDemande = statutDemande;
	}

}
