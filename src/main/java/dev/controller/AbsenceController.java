package dev.controller;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.dto.DtoAbsenceResponse;
import dev.domain.dto.DtoCreerAbsenceRequest;
import dev.domain.entite.Absence;
import dev.domain.entite.Collegue;
import dev.domain.entite.EStatutDemandeAbsence;
import dev.domain.entite.ETypeJourAbsence;
import dev.domain.exceptions.CollegueIntrouvableException;
import dev.domain.services.AbsenceService;
import dev.domain.services.CollegueService;

@RestController
@RequestMapping("absence") // http://localhost:4200/connexion
public class AbsenceController {

	private CollegueService collegueService;
	private AbsenceService absenceService;

	public AbsenceController(CollegueService collegueService, AbsenceService absenceService) {
		this.collegueService = collegueService;
		this.absenceService = absenceService;
	}

	@GetMapping("all")
	public ResponseEntity<?> listerAllAbsences() throws CollegueIntrouvableException {
		List<Absence> absences = this.absenceService.getAllAbsence();

		List<DtoAbsenceResponse> listeAbsenceDto = new ArrayList<DtoAbsenceResponse>();

		for (Absence abs : absences) {
			listeAbsenceDto.add(new DtoAbsenceResponse(abs));
		}

		if (absences.size() != 0) {
			return ResponseEntity.ok(listeAbsenceDto);
		} else {
			return ResponseEntity.ok("Aucune absence enregistrée");
		}
	}

	@PostMapping("create")
	public ResponseEntity<?> creerAbsence(@RequestBody @Valid DtoCreerAbsenceRequest dtoRequest, BindingResult resValid)
			throws CollegueIntrouvableException {
		if (!resValid.hasErrors()) {
			LocalDate dateDebutToLocalData = Instant.ofEpochMilli(dtoRequest.getDatePremierJourAbsence().getTime())
					.atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate dateFinToLocalData = Instant.ofEpochMilli(dtoRequest.getDateDernierJourAbsence().getTime())
					.atZone(ZoneId.systemDefault()).toLocalDate();
			Collegue collegueCreantAbsence = this.collegueService.recupererCollegue(dtoRequest.getIdCollegue());

			Absence absence = this.absenceService.creerAbsence(new Absence(dateDebutToLocalData, dateFinToLocalData,
					ETypeJourAbsence.valueOf(dtoRequest.getTypeConge()), dtoRequest.getCommentaireAbsence(),
					EStatutDemandeAbsence.INITIALE, collegueCreantAbsence));

			DtoAbsenceResponse dtoAbsence = new DtoAbsenceResponse(absence);

			return ResponseEntity.status(HttpStatus.OK).body(dtoAbsence);
		} else {
			return ResponseEntity.badRequest().body("Problème survenu lors du Post");
		}
	}
}
