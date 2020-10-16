package dev.domain.services;

import org.springframework.stereotype.Service;

import dev.domain.exceptions.AbsenceIntrouvableException;
import dev.repository.AbsenceRepo;

@Service
public class HistogrammeService {

	private AbsenceRepo absenceRepo;

	public HistogrammeService(AbsenceRepo absenceRepo) {
		super();
		this.absenceRepo = absenceRepo;
	}

	public void creationHistograme() throws AbsenceIntrouvableException {

	}
}
