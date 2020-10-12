package dev.domain.dto;

import java.time.LocalDate;
import dev.domain.entite.Absence;

public class DtoAbsenceByUserResponse {

	LocalDate datePremierJourAbsence;
	LocalDate dateDernierJourAbsence;
	String typeConge;
	String commentaireAbsence;
	String statutDemande;

	Long nbJourAbsence;

	public DtoAbsenceByUserResponse(Absence absence, Long nbJourAbsence) {

		this.datePremierJourAbsence = absence.getDatePremierJourAbsence();
		this.dateDernierJourAbsence = absence.getDateDernierJourAbsence();
		this.typeConge = absence.getTypeConge().toString();
		this.commentaireAbsence = absence.getCommentaireAbsence();
		this.statutDemande = absence.getStatutDemandeAbsence().toString();
		this.nbJourAbsence = nbJourAbsence;
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
