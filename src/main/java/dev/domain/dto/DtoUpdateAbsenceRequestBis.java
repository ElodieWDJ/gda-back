package dev.domain.dto;

import java.util.Date;

public class DtoUpdateAbsenceRequestBis {
	long idAbsence;
	static Date datePremierJourAbsence;
	static Date dateDernierJourAbsence;
	String typeConge;
	String commentaireAbsence;
	
	public DtoUpdateAbsenceRequestBis(long idAbsence, String typeConge, String commentaireAbsence
			) {
		this.idAbsence = idAbsence;
		this.typeConge = typeConge;
		this.commentaireAbsence = commentaireAbsence;

	}

	public long getIdAbsence() {
		return idAbsence;
	}

	public void setIdAbsence(long idAbsence) {
		this.idAbsence = idAbsence;
	}

	public static Date getDatePremierJourAbsence() {
		return datePremierJourAbsence;
	}

	public static void setDatePremierJourAbsence(Date datePremierJourAbsence) {
		DtoUpdateAbsenceRequestBis.datePremierJourAbsence = datePremierJourAbsence;
	}

	public static Date getDateDernierJourAbsence() {
		return dateDernierJourAbsence;
	}

	public static void setDateDernierJourAbsence(Date dateDernierJourAbsence) {
		DtoUpdateAbsenceRequestBis.dateDernierJourAbsence = dateDernierJourAbsence;
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
