package dev.domain.services;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.domain.entite.Absence;
import dev.domain.entite.Collegue;
import dev.domain.enums.EStatutDemandeAbsence;
import dev.domain.enums.ETypeJourAbsence;
import dev.domain.exceptions.AbsenceIntrouvableException;
import dev.domain.exceptions.CollegueIntrouvableException;
import dev.repository.AbsenceRepo;
import dev.repository.CollegueRepo;
import dev.utils.DateUtils;

@Service
public class AbsenceService {
	private AbsenceRepo absenceRepo;
	private CollegueRepo collegueRepo;
	private DateUtils dateUtils;
	
	public AbsenceService(AbsenceRepo absenceRepo, CollegueRepo collegueRepo, DateUtils dateUtils) {
		this.absenceRepo = absenceRepo;
		this.collegueRepo = collegueRepo;
		this.dateUtils = dateUtils;
	}

	public Absence creerAbsence(Absence absence) {
		return this.absenceRepo.save(absence);
	}
	
	public List<Absence> getAllAbsence() {
		return this.absenceRepo.findAll();
	}

	public List<Absence> getAbsencesByUser(Long id) throws CollegueIntrouvableException {

		Optional<Collegue> collegue = collegueRepo.findById(id);

		if (collegue.isPresent()) {
			Collegue col = collegue.get();
			return col.getListeAbsencesDuCollegue();
			
		} else {
			throw new CollegueIntrouvableException("Pas de collègue correspondant à cet Id.");
		}

	}

	// retourne une liste d'objet de ttes les absences


	public boolean controleChevaucheDate(LocalDate dateDebut, LocalDate dateFin, Collegue collegue) {
		List<Absence> absences = collegue.getListeAbsencesDuCollegue();
		boolean controleValide = true;
		
		if(absences.size() != 0) {
			for(Absence absence : absences) {
				controleValide = this.checkerDate(dateDebut, dateFin, absence);
			}	
		}
		return controleValide;
	}

	
	private boolean checkerDate(LocalDate dateDebut, LocalDate dateFin, Absence absence) {
		if(dateDebut.isBefore(absence.getDatePremierJourAbsence()) && dateFin.isBefore(absence.getDateDernierJourAbsence())
			|| dateDebut.isBefore(absence.getDatePremierJourAbsence()) && dateFin.isAfter(absence.getDateDernierJourAbsence())
			|| dateDebut.isAfter(absence.getDatePremierJourAbsence()) && dateDebut.isBefore(absence.getDateDernierJourAbsence())
			|| dateDebut.isAfter(absence.getDatePremierJourAbsence()) && dateFin.isBefore(absence.getDateDernierJourAbsence())
			|| dateDebut.isEqual(absence.getDatePremierJourAbsence()) 
		)	{
			
			return this.checkerStatusAbsence(absence) ? true : false;
		} else {
			return true;
		}
	}

	private boolean checkerStatusAbsence(Absence absence) {
		return absence.getStatutDemandeAbsence().equals(EStatutDemandeAbsence.REJETEE) ? true : false;
	}
	
	public LocalDate getDateMax(String annee, String mois) throws ParseException {
		Date date = this.dateUtils.getLastDayOfMonth(annee, mois);
		return DateUtils.convertDateToLocalDate(date);
		
	}
	
	/**
	 * 
	 * @param id identifiant de l'absence recherchée
	 * @return un objet Absence correspondant à l'absence
	 */
	public Optional<Absence> getById(Long id) {
		return absenceRepo.findById(id);
	}


	public Absence updateAbsence(Absence absUpdated) {
		return absenceRepo.save(absUpdated);

	}

	public Optional<List<Absence>> getAllAbsenceByDateInterval(LocalDate dateDebutChoisi, LocalDate dateFinChoisi) {
		return this.absenceRepo.findByDatePremierJourAbsenceGreaterThanEqualAndDateDernierJourAbsenceLessThan(dateDebutChoisi, dateFinChoisi);
	}

	public List<Absence> getAllRttEtJoursFeries(Integer annee) throws AbsenceIntrouvableException {
		Optional<List<Absence>> listAbsence = absenceRepo.findAllJoursFerieEtRttEmployeur();
		List<Absence> listAbsenceAnnee = new ArrayList<Absence>();

		if (listAbsence.isPresent()) {
			for (Absence absence : listAbsence.get()) {
				if (absence.getDateDernierJourAbsence().getYear() == annee) {
					listAbsenceAnnee.add(absence);
				}
			}
		} else {
			throw new AbsenceIntrouvableException("Pas de RTT ou de Jours feriés cette année");
		}

		return listAbsenceAnnee;
	}
	
	public Optional<List<Absence>> getAllAbsenceEnAttente() {
		return this.absenceRepo.findByStatutDemandeAbsence(EStatutDemandeAbsence.EN_ATTENTE_VALIDATION);
	}
	
	public Optional<List<Absence>> getAllAbsenceValidee() {
		return this.absenceRepo.findByStatutDemandeAbsence(EStatutDemandeAbsence.VALIDEE);
	}
	
	
}

