package dev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.services.AbsenceService;

@RestController
@RequestMapping("vueSynthetique")
public class VueSynthetiqueController {
	private AbsenceService absenceService;
	
	public VueSynthetiqueController(AbsenceService absenceService) {
		this.absenceService = absenceService;
	}
	
	@GetMapping("calendrier")
	public ResponseEntity<?> getCalendrierMoisSynthetique() {
		
		return ResponseEntity.ok("let's go");
	}
}
