package dev.domain.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.domain.entite.Absence;
import dev.domain.entite.Collegue;
import dev.domain.enums.EStatutDemandeAbsence;
import dev.domain.enums.ETypeJourAbsence;
import dev.domain.exceptions.CollegueIntrouvableException;
import dev.repository.AbsenceRepo;
import dev.repository.CollegueRepo;

@Service
public class AbsenceService {
	private AbsenceRepo absenceRepo;
	private CollegueRepo collegueRepo;

	public AbsenceService(AbsenceRepo absenceRepo, CollegueRepo collegueRepo) {
		this.absenceRepo = absenceRepo;
		this.collegueRepo = collegueRepo;
	}

	public Absence creerAbsence(Absence newAbsence) {
		Collegue collegue = newAbsence.getCollegue();

		if (newAbsence.getTypeConge() == ETypeJourAbsence.CONGE_PAYE) {
			collegue.setSoldesCP(collegue.getSoldesCP() - newAbsence.getNbJoursAbsence());
		} else if (newAbsence.getTypeConge() == ETypeJourAbsence.RTT) {
			collegue.setSoldesRTT(collegue.getSoldesRTT() - newAbsence.getNbJoursAbsence());
			System.out.println("test");
		}

		this.collegueRepo.save(collegue);
		return this.absenceRepo.save(newAbsence);
	}
	// retourne une liste d'objet de ttes les absences

	public boolean controleChevaucheDate(LocalDate dateDebut, LocalDate dateFin, Collegue collegue) {
		List<Absence> absences = collegue.getListeAbsencesDuCollegue();
		boolean controleValide = true;

		if (absences.size() != 0) {
			for (Absence absence : absences) {
				controleValide = this.checkerDate(dateDebut, dateFin, absence);
			}
		}
		return controleValide;
	}

	private boolean checkerDate(LocalDate dateDebut, LocalDate dateFin, Absence absence) {
		if (dateDebut.isBefore(absence.getDatePremierJourAbsence())
				&& dateFin.isBefore(absence.getDateDernierJourAbsence())
				|| dateDebut.isBefore(absence.getDatePremierJourAbsence())
						&& dateFin.isAfter(absence.getDateDernierJourAbsence())
				|| dateDebut.isAfter(absence.getDatePremierJourAbsence())
						&& dateDebut.isBefore(absence.getDateDernierJourAbsence())
				|| dateDebut.isAfter(absence.getDatePremierJourAbsence())
						&& dateFin.isBefore(absence.getDateDernierJourAbsence())
				|| dateDebut.isEqual(absence.getDatePremierJourAbsence())
				|| dateFin.isEqual(absence.getDateDernierJourAbsence())) {

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

	/**
	 * 
	 * @param id identifiant de l'absence recherchée
	 * @return un objet Absence correspondant à l'absence
	 */
	public Optional<Absence> getById(Long id) {
		return absenceRepo.findById(id);
	}

	public Absence updateAbsence(Absence absUpdated) {
		Optional<Absence> absenceToUpdate = this.getById(absUpdated.getId());
		if (absenceToUpdate.isPresent()) {
			absenceToUpdate.get().setDatePremierJourAbsence(absUpdated.getDatePremierJourAbsence());
			absenceToUpdate.get().setDateDernierJourAbsence(absUpdated.getDateDernierJourAbsence());
			absenceToUpdate.get().setTypeConge(absUpdated.getTypeConge());
			absenceToUpdate.get().setCommentaireAbsence(absUpdated.getCommentaireAbsence());
			absenceToUpdate.get().setStatutDemandeAbsence(absUpdated.getStatutDemandeAbsence());
		}
		return absenceRepo.save(absenceToUpdate.get());
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
