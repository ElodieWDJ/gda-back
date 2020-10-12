package dev.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.domain.entite.Absence;
import dev.repository.AbsenceRepo;

@Service
public class AbsenceService {
	private AbsenceRepo absenceRepo;

	public AbsenceService(AbsenceRepo absenceRepo) {
		this.absenceRepo = absenceRepo;
	}

	public Absence creerAbsence(Absence absence) {
		return this.absenceRepo.save(absence);
	}

	// retourne une liste d'objet de ttes les absences
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

	public Absence updateAbsence(Long id, Date datePremierJourAbsence, Date dateDernierJourAbsence, String typeConge,
			String commentaireAbsence, String statut) {
		Optional<Absence> absenceToUpdate = this.getById(id);
		if (absenceToUpdate.isPresent()) {
			absenceToUpdate.get().setDatePremierJourAbsence(datePremierJourAbsence);
			absenceToUpdate.get().setDateDernierJourAbsence(dateDernierJourAbsence);
			absenceToUpdate.get().setTypeConge(typeConge);
			absenceToUpdate.get().setCommentaireAbsence(commentaireAbsence);
			absenceToUpdate.get().setStatutDemandeAbsence(statut);
		}
		return absenceRepo.save(absenceToUpdate.get());
	}
	// c'est moi
}
