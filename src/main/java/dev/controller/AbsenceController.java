package dev.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Absence;
import dev.domain.exceptions.CollegueIntrouvableException;
import dev.domain.services.CollegueService;

@RestController
@RequestMapping("gestion/collegue")
public class AbsenceController {
	private CollegueService collegueService;

	public AbsenceController(CollegueService collegueService) {
		this.collegueService = collegueService;
	}

	@GetMapping("absences/all")
	public ResponseEntity<?> listerAbsencesAllCollegues(@PathVariable Long id) throws CollegueIntrouvableException {
		List<Absence> absences = this.collegueService.getAllAbsences(id);
		if (absences.size() != 0) {
			return ResponseEntity.ok(absences);
		} else {
			return ResponseEntity.ok("Aucune absence");
		}
	}


}
