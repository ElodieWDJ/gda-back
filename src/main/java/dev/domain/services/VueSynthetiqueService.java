package dev.domain.services;


import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.domain.entite.Absence;
import dev.domain.enums.ETypeJourAbsence;
import dev.utils.DateUtils;

@Service
public class VueSynthetiqueService {
	private AbsenceService absenceService;
	private DateUtils dateUtils;
	
	public VueSynthetiqueService(AbsenceService absenceService, DateUtils dateUtils) {
		this.absenceService = absenceService;
		this.dateUtils = dateUtils;
	}
	
	public Optional<List<Absence>> listeAbsenceValideByInterval(String moisSelectionne, String anneeSelectionne) throws ParseException {
		LocalDate dateDebut = DateUtils.convertStringToLocalDate("01", moisSelectionne, anneeSelectionne);
		LocalDate dateFinDuMois = DateUtils.getLastDateOfMonth(moisSelectionne , anneeSelectionne);
		
		return this.absenceService.getAllAbsenceValideByInterval(dateDebut, dateFinDuMois);
	}
	
	public String formatteTypeJourAbsence(ETypeJourAbsence type) {
		return type.toString().substring(0, 1).toUpperCase();
	}
}
