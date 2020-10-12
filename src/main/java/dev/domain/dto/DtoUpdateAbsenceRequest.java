package dev.domain.dto;

import java.util.Date;

public class DtoUpdateAbsenceRequest {

	long idCollegue;
	Date datePremierJourAbsence;
	Date dateDernierJourAbsence;
	String typeConge;
	String commentaireAbsence;
	String statutDemande;

	/**
	 * @return the idCollegue
	 */
	public long getIdCollegue() {
		return idCollegue;
	}

	/**
	 * @param idCollegue the idCollegue to set
	 */
	public void setIdCollegue(long idCollegue) {
		this.idCollegue = idCollegue;
	}

	/**
	 * @return the datePremierJourAbsence
	 */
	public Date getDatePremierJourAbsence() {
		return datePremierJourAbsence;
	}

	/**
	 * @param datePremierJourAbsence the datePremierJourAbsence to set
	 */
	public void setDatePremierJourAbsence(Date datePremierJourAbsence) {
		this.datePremierJourAbsence = datePremierJourAbsence;
	}

	/**
	 * @return the dateDernierJourAbsence
	 */
	public Date getDateDernierJourAbsence() {
		return dateDernierJourAbsence;
	}

	/**
	 * @param dateDernierJourAbsence the dateDernierJourAbsence to set
	 */
	public void setDateDernierJourAbsence(Date dateDernierJourAbsence) {
		this.dateDernierJourAbsence = dateDernierJourAbsence;
	}

	/**
	 * @return the typeConge
	 */
	public String getTypeConge() {
		return typeConge;
	}

	/**
	 * @param typeConge the typeConge to set
	 */
	public void setTypeConge(String typeConge) {
		this.typeConge = typeConge;
	}

	/**
	 * @return the commentaireAbsence
	 */
	public String getCommentaireAbsence() {
		return commentaireAbsence;
	}

	/**
	 * @param commentaireAbsence the commentaireAbsence to set
	 */
	public void setCommentaireAbsence(String commentaireAbsence) {
		this.commentaireAbsence = commentaireAbsence;
	}

	/**
	 * @return the statutDemande
	 */
	public String getStatutDemande() {
		return statutDemande;
	}

	/**
	 * @param statutDemande the statutDemande to set
	 */
	public void setStatutDemande(String statutDemande) {
		this.statutDemande = statutDemande;
	}

}