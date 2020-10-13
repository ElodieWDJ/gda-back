package dev.domain.dto;

import java.time.LocalDate;

import dev.domain.entite.Absence;

public class DtoAbsenceResponse {

	long idCollegue;
	long idAbsence;
	LocalDate datePremierJourAbsence;
	LocalDate dateDernierJourAbsence;
	String typeConge;
	String commentaireAbsence;
	String statutDemande;

	public DtoAbsenceResponse(Absence abs) {
		this.idAbsence = abs.getId();
		this.idCollegue = abs.getCollegue().getId();
		this.datePremierJourAbsence = abs.getDatePremierJourAbsence();
		this.dateDernierJourAbsence = abs.getDateDernierJourAbsence();
		this.typeConge = abs.getTypeConge().toString();
		this.commentaireAbsence = abs.getCommentaireAbsence();
		this.statutDemande = abs.getStatutDemandeAbsence().toString();
	}

	public long getIdAbsence() {
		return idAbsence;
	}

	public void setIdAbsence(long idAbsence) {
		this.idAbsence = idAbsence;
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
