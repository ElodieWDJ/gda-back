package dev.domain.dto;

import java.time.LocalDate;

public class DtoCreerJourFerieRequest {
	long idCollegue;
	LocalDate dateDuJour;
	String typeConge;
	String commentaireAbsence;

	public DtoCreerJourFerieRequest(long idCollegue, LocalDate dateDuJour, String typeConge,
			String commentaireAbsence) {
		super();
		this.idCollegue = idCollegue;
		this.dateDuJour = dateDuJour;
		this.typeConge = typeConge;
		this.commentaireAbsence = commentaireAbsence;
	}

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
	 * @return the dateDuJour
	 */
	public LocalDate getDateDuJour() {
		return dateDuJour;
	}

	/**
	 * @param dateDuJour the dateDuJour to set
	 */
	public void setDateDuJour(LocalDate dateDuJour) {
		this.dateDuJour = dateDuJour;
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

}
