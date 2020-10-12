package dev.domain.services;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.springframework.stereotype.Service;

import dev.domain.entite.Absence;
import dev.domain.entite.Collegue;
import dev.domain.enums.EStatutDemandeAbsence;
import dev.domain.exceptions.CollegueIntrouvableException;
import dev.repository.AbsenceRepo;
import dev.repository.CollegueRepo;
import dev.domain.enums.ETypeJourAbsence;

@Service
public class AbsenceService {
	private AbsenceRepo absenceRepo;
	private CollegueRepo collegueRepo;

	
	public AbsenceService(AbsenceRepo absenceRepo, CollegueRepo collegueRepo) {
		this.absenceRepo = absenceRepo;
		this.collegueRepo = collegueRepo;
	}

	public Absence creerAbsence(Absence absence) {
		Collegue collegue = absence.getCollegue();
		// collegue.setSoldesCP(0);
		//	collegue.setSoldesRTT(soldesRTT);
		if (absence.getTypeConge() == ETypeJourAbsence.CONGE_PAYE) {
			
		} else if(absence.getTypeConge() == ETypeJourAbsence.RTT) {
			
		}
		return this.absenceRepo.save(absence);
	}


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
			|| dateDebut.isEqual(absence.getDatePremierJourAbsence()) ||  dateFin.isEqual(absence.getDateDernierJourAbsence())
		)	{
			
			return this.checkerStatusAbsence(absence) ? true : false;
		} else {
			return true;
		}
	}

	private boolean checkerStatusAbsence(Absence absence) {
		return absence.getStatutDemandeAbsence().equals(EStatutDemandeAbsence.REJETEE) ? true : false;
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
	
	

}
