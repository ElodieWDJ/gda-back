package dev.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.dto.DtoCreerJourFerieRequest;
import dev.domain.entite.Collegue;
import dev.domain.entite.JourFerie;
import dev.domain.enums.ETypeJourAbsence;
import dev.domain.exceptions.CollegueIntrouvableException;
import dev.domain.services.CollegueService;
import dev.domain.services.JourFerieService;

@RestController
@RequestMapping("jourFerie")
public class JourFerieController {

	private CollegueService collegueService;
	private JourFerieService jourFerieService;

	public JourFerieController(CollegueService collegueService, JourFerieService jourFerieService) {
		this.collegueService = collegueService;
		this.jourFerieService = jourFerieService;
	}

	@PostMapping("create")
	public ResponseEntity<?> creerJourFerie(@RequestBody @Valid DtoCreerJourFerieRequest dtoRequest,
			BindingResult resValid) throws CollegueIntrouvableException {
		Collegue collegueCreantJourFerie = this.collegueService.recupererCollegue(dtoRequest.getIdCollegue());
		JourFerie jourFerie = new JourFerie(dtoRequest.getDateDuJour(),
				ETypeJourAbsence.valueOf(dtoRequest.getTypeConge()), dtoRequest.getCommentaireAbsence(),
				this.collegueService.recupererCollegue(dtoRequest.getIdCollegue()));
		return ResponseEntity.status(HttpStatus.OK).body(this.jourFerieService.creerJourFerie(jourFerie));

	}

}
