package dev.domain.dto;

import java.time.LocalDate;
import java.util.List;

import dev.domain.entite.Absence;
import dev.domain.enums.ETypeJourAbsence;

public class DtoCalendrierSynthetiqueResponse {
	private String nomCollegue;
	private String typeCongePremiereLettre;
	private LocalDate datePremierJourAbsence;
	private LocalDate dateDernierJourAbsence;
	
	public DtoCalendrierSynthetiqueResponse(Absence absence) {
		this.nomCollegue = absence.getCollegue().getNom();
		this.typeCongePremiereLettre = this.formatteTypeJourAbsence(absence.getTypeConge());
		this.datePremierJourAbsence = absence.getDatePremierJourAbsence();
		this.dateDernierJourAbsence = absence.getDateDernierJourAbsence();
		
	}
	
	
	public String formatteTypeJourAbsence(ETypeJourAbsence type) {
		return type.toString().substring(0, 1).toUpperCase();
	}
	

}
