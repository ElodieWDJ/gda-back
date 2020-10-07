package dev.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.domain.Absence;
import dev.domain.Collegue;
import dev.domain.exceptions.CollegueIntrouvableException;
import dev.repository.CollegueRepo;

@Service
public class AbsenceService {
	private CollegueRepo collegueRepo;
	
	public AbsenceService(CollegueRepo collegueRepo) {
		this.collegueRepo = collegueRepo;
	}

	
}
