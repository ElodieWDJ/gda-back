package dev.domain.services;

import org.springframework.stereotype.Service;

import dev.domain.entite.JourFerie;
import dev.repository.CollegueRepo;
import dev.repository.JourFerieRepo;

@Service
public class JourFerieService {
	private JourFerieRepo jourFerieRepo;
	private CollegueRepo collegueRepo;

	public JourFerieService(JourFerieRepo jourFerieRepo, CollegueRepo collegueRepo) {
		this.jourFerieRepo = jourFerieRepo;
		this.collegueRepo = collegueRepo;
	}

	public JourFerie creerJourFerie(JourFerie newJourFerie) {
//		Collegue collegue = newJourFerie.getCollegue();
		return this.jourFerieRepo.save(newJourFerie);

//		if (newJourFerie.getTypeJourAbsence() == ETypeJourAbsence.JOUR_FERIE) {
//			collegue.setTypeJourAbsence()
		// }
	}

}
