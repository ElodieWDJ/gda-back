package dev.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.domain.entite.Absence;
import dev.domain.entite.Collegue;
import dev.domain.exceptions.CollegueIntrouvableException;
import dev.repository.AbsenceRepo;
import dev.repository.CollegueRepo;

@Service
public class AbsenceService {
	private AbsenceRepo absenceRepo;

	public AbsenceService(AbsenceRepo absenceRepo) {
		this.absenceRepo = absenceRepo;
	}

	public Absence creerAbsence(Absence absence) {
		return this.absenceRepo.save(absence);
	}

	public List<Absence> getAllAbsence() {
		return this.absenceRepo.findAll();
	}
}
