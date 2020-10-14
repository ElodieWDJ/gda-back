package dev.domain.dto;

import java.time.LocalDate;

import dev.domain.entite.Absence;

public class DtoHistogrammeResponse {
	private String nomCollegue;
	private LocalDate datePremierJourAbsence;
	private LocalDate dateDernierJourAbsence;

	public DtoHistogrammeResponse(Absence absence) {
		this.nomCollegue = absence.getCollegue().getNom();
		this.datePremierJourAbsence = absence.getDatePremierJourAbsence();
		this.dateDernierJourAbsence = absence.getDateDernierJourAbsence();
	}

	public String getNomCollegue() {
		return nomCollegue;
	}

	public void setNomCollegue(String nomCollegue) {
		this.nomCollegue = nomCollegue;
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
}	
