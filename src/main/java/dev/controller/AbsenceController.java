package dev.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Absence;
import dev.domain.exceptions.CollegueIntrouvableException;
import dev.domain.services.AbsenceService;

@RestController
@RequestMapping("gestion/absences")
public class AbsenceController {
	private AbsenceService absenceService;
	
	public AbsenceController(AbsenceService absenceService) {
		this.absenceService = absenceService;
	}
	
	@GetMapping("/all/{idUtilisateur}")
	public ResponseEntity<?> listeAbsencesParUtilisateur(@PathVariable Long id) throws CollegueIntrouvableException {
		List<Absence> absences = this.absenceService.getAbsencesByCollegue(id);
		if(absences.size() != 0) {
			return ResponseEntity.ok(absences);
		} else {
			return ResponseEntity.ok("Aucune absence");
		}
	}
}
