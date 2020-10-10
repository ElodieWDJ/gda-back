package dev.domain.dto;

import java.util.Date;

public class DtoCreerAbsenceRequest {

	long idCollegue;
	Date datePremierJourAbsence;
	Date dateDernierJourAbsence;
	String typeConge;
	String commentaireAbsence;
	String statutDemande;

	public DtoCreerAbsenceRequest(long idCollegue, Date datePremierJourAbsence, Date dateDernierJourAbsence,
			String typeConge, String commentaireAbsence, String statutDemande) {
		super();
		this.idCollegue = idCollegue;
		this.datePremierJourAbsence = datePremierJourAbsence;
		this.dateDernierJourAbsence = dateDernierJourAbsence;
		this.typeConge = typeConge;
		this.commentaireAbsence = commentaireAbsence;
		this.statutDemande = statutDemande;
	}

	public long getIdCollegue() {
		return idCollegue;
	}

	public void setIdCollegue(long idCollegue) {
		this.idCollegue = idCollegue;
	}

	public Date getDatePremierJourAbsence() {
		return datePremierJourAbsence;
	}

	public void setDatePremierJourAbsence(Date datePremierJourAbsence) {
		this.datePremierJourAbsence = datePremierJourAbsence;
	}

	public Date getDateDernierJourAbsence() {
		return dateDernierJourAbsence;
	}

	public void setDateDernierJourAbsence(Date dateDernierJourAbsence) {
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
