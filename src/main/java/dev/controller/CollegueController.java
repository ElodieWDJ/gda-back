package dev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.entite.Collegue;
import dev.domain.exceptions.CollegueIntrouvableException;
import dev.domain.services.CollegueService;

@RestController
@RequestMapping("collegue")
public class CollegueController {

	private CollegueService collegueService;

	public CollegueController(CollegueService collegueService) {
		super();
		this.collegueService = collegueService;
	}

	@GetMapping("nbCongePayeRestants")
	public ResponseEntity<?> getNbCongePayeRestants(@RequestParam int idUtilisateur)
			throws CollegueIntrouvableException {
		Collegue collegue = this.collegueService.recupererCollegue(Integer.toUnsignedLong(idUtilisateur));
		return ResponseEntity.ok(collegue.getSoldesCP());
	}

	@GetMapping("nbRttRestants")
	public ResponseEntity<?> getNbRttRestants() throws CollegueIntrouvableException {

		return null;
	}

	/*
	 * @GetMapping("visualisation/nbJourRestant") public int
	 * afficherNbJourRestant(@PathVariable Long idUtilisateur) throws
	 * CollegueIntrouvableException { Collegue collegue =
	 * collegueService.recupererCollegue(idUtilisateur);
	 * 
	 * return this.collegueService.getNbJourCongeRestant(); }
	 */

}
