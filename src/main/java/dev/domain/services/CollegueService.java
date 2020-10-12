package dev.domain.services;

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
			return collegue.get().getSoldesVacances();
		} else {
			throw new CollegueIntrouvableException("L'id ne correspond à aucun collegue. ID introuvable = " + id);
		}

	}
}
