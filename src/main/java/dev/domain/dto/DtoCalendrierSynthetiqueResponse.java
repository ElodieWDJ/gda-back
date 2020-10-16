package dev.domain.dto;

import java.time.LocalDate;
import java.util.List;

import dev.domain.entite.Absence;
import dev.domain.enums.ETypeJourAbsence;
import dev.utils.DateUtils;

public class DtoCalendrierSynthetiqueResponse {
	private String nomCollegue;
	private String typeCongePremiereLettre;
	private LocalDate datePremierJourAbsence;
	private LocalDate dateDernierJourAbsence;
	private Integer joursMaxMois;
	
	public DtoCalendrierSynthetiqueResponse(Absence absence, Integer joursMaxMois) {
		this.nomCollegue = absence.getCollegue().getNom();
		this.typeCongePremiereLettre = this.formatteTypeJourAbsence(absence.getTypeConge());
		this.datePremierJourAbsence = absence.getDatePremierJourAbsence();
		this.dateDernierJourAbsence = absence.getDateDernierJourAbsence();
		this.joursMaxMois = joursMaxMois;
		
	}
	
	public String getNomCollegue() {
		return nomCollegue;
	}

	public void setNomCollegue(String nomCollegue) {
		this.nomCollegue = nomCollegue;
	}

	public String getTypeCongePremiereLettre() {
		return typeCongePremiereLettre;
	}

	public void setTypeCongePremiereLettre(String typeCongePremiereLettre) {
		this.typeCongePremiereLettre = typeCongePremiereLettre;
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

	public String formatteTypeJourAbsence(ETypeJourAbsence type) {
		return type.toString().substring(0, 1).toUpperCase();
	}

	public Integer getJoursMaxMois() {
		return joursMaxMois;
	}

	public void setJoursMaxMois(Integer joursMaxMois) {
		this.joursMaxMois = joursMaxMois;
	}
	
	
}
