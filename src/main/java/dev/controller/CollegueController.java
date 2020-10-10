package dev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.exceptions.CollegueIntrouvableException;
import dev.domain.services.CollegueService;

@RestController
@RequestMapping("absence")
public class CollegueController {

	private CollegueService collegueService;

	public CollegueController(CollegueService collegueService) {
		super();
		this.collegueService = collegueService;
	}
	
	@GetMapping("soldeVacances")
	public ResponseEntity<?> soldeRestantesVacances() throws CollegueIntrouvableException {
		
		
		return null;
	}

}
