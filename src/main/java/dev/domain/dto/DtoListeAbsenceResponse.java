package dev.domain.dto;

import java.time.LocalDate;

public class DtoListeAbsenceResponse {
	private LocalDate datePremierJourAbsence;
	private LocalDate dateDernierJourAbsence;
	private String typeConge;

	public DtoListeAbsenceResponse(LocalDate datePremierJourAbsence, LocalDate dateDernierJourAbsence,
			String typeConge) {
		this.datePremierJourAbsence = datePremierJourAbsence;
		this.dateDernierJourAbsence = dateDernierJourAbsence;
		this.typeConge = typeConge;
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
	
	
}
