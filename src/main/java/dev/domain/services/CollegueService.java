package dev.domain.services;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.domain.entite.Absence;
import dev.domain.entite.Collegue;
import dev.domain.exceptions.CollegueIntrouvableException;
import dev.repository.CollegueRepo;

@Service
public class CollegueService {
	private CollegueRepo collegueRepo;

	public CollegueService(CollegueRepo collegueRepo) {
		this.collegueRepo = collegueRepo;
	}

	public List<Collegue> getAllCollegues() {
		return this.collegueRepo.findAll();
	}

	public List<Absence> getAllAbsences(Long id) throws CollegueIntrouvableException {
		Collegue collegue = this.recupererCollegue(id);
		return collegue.getListeAbsencesDuCollegue();
	}

	public Collegue recupererCollegue(Long id) throws CollegueIntrouvableException {
		Optional<Collegue> collegue = this.collegueRepo.findById(id);

		if (collegue.isPresent()) {
			return collegue.get();
		} else {
			throw new CollegueIntrouvableException("L'id ne correspond à aucun collegue. ID introuvable = " + id);
		}
	}

	public Integer recupererSoldeTotal(Long id) throws CollegueIntrouvableException {
		Optional<Collegue> collegue = this.collegueRepo.findById(id);
		if (collegue.isPresent()) {
			return null;
		} else {
			throw new CollegueIntrouvableException("L'id ne correspond à aucun collegue. ID introuvable = " + id);
		}

	}

	public Integer getNbJourCongeRestant(Absence absence) {
		Integer nbJourParAbsence = null;
		Integer nbJourRestant = null;
		// initialise pour switcher de localdate en date
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Collegue collegue = new Collegue();

		try {
			LocalDate dateAvant = absence.getDatePremierJourAbsence();
			System.out.println("test : " + dateAvant);
			LocalDate dateApres = absence.getDateDernierJourAbsence();
			// passage du LocalDate en Date
			Date date1 = Date.from(dateAvant.atStartOfDay(defaultZoneId).toInstant());

			Date date2 = Date.from(dateApres.atStartOfDay(defaultZoneId).toInstant());

			// passage du long en Integer
			Long dateApresSoustraction = Math.abs(date2.getTime() - date1.getTime());
			// compte les bites
			nbJourParAbsence = dateApresSoustraction.bitCount(dateApresSoustraction);
			// nbJourRestant = collegue.getSoldesVacances()-nbJourParAbsence;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return nbJourRestant;
	}
}
