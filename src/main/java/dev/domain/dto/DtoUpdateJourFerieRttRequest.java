package dev.domain.dto;

import java.util.Date;

public class DtoUpdateJourFerieRttRequest {

	Date datePremierJourAbsence;
	String typeConge;
	String commentaireAbsence;

	public Date getDatePremierJourAbsence() {
		return datePremierJourAbsence;
	}

	public void setDatePremierJourAbsence(Date datePremierJourAbsence) {
		this.datePremierJourAbsence = datePremierJourAbsence;
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

}
