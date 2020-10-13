package dev.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.dto.DtoAbsenceExistanteResponse;
import dev.domain.dto.DtoAbsenceResponse;
import dev.domain.dto.DtoAucuneAbsenceResponse;
import dev.domain.dto.DtoCreerAbsenceRequest;
import dev.domain.dto.DtoJoursFerieResponse;
import dev.domain.entite.Absence;
import dev.domain.entite.Collegue;
import dev.domain.enums.EStatutDemandeAbsence;
import dev.domain.enums.ETypeJourAbsence;
import dev.domain.exceptions.AbsenceIntrouvableException;
import dev.domain.exceptions.CollegueIntrouvableException;
import dev.domain.services.AbsenceService;
import dev.domain.services.CollegueService;
import dev.utils.ConverterDate;

@RestController
@RequestMapping("absence") // http://localhost:4200/connexion
public class AbsenceController {

	private CollegueService collegueService;
	private AbsenceService absenceService;

	public AbsenceController(CollegueService collegueService, AbsenceService absenceService) {
		this.collegueService = collegueService;
		this.absenceService = absenceService;
	}

	@GetMapping("visualisation/user/{id}")
	public ResponseEntity<?> listerAbsencesByUser(@PathVariable Long id) throws CollegueIntrouvableException {
		List<Absence> absences = this.absenceService.getAbsencesByUser(id);
		List<DtoAbsenceResponse> listeAbsenceDto = absences.stream().map(abs -> new DtoAbsenceResponse(abs))
				.collect(Collectors.toList());

		return (absences.size() != 0) ? ResponseEntity.ok(listeAbsenceDto)
				: ResponseEntity.ok(new DtoAucuneAbsenceResponse("Aucune absence enregistrée"));
	}

	@GetMapping("all")
	public ResponseEntity<?> listerAllAbsences() throws CollegueIntrouvableException {
		List<Absence> absences = this.absenceService.getAllAbsence();
		List<DtoAbsenceResponse> listeAbsenceDto = absences.stream().map(abs -> new DtoAbsenceResponse(abs))
				.collect(Collectors.toList());

		return (absences.size() != 0) ? ResponseEntity.ok(listeAbsenceDto)
				: ResponseEntity.ok(new DtoAucuneAbsenceResponse("Aucune absence enregistrée"));
	}

	@GetMapping("joursferies/{annee}")
	public ResponseEntity<?> listerAllJoursFeriesEtRttEmployeur(@RequestParam Integer annee)
			throws AbsenceIntrouvableException {
		List<Absence> absences = this.absenceService.getAllRttEtJoursFeries(annee);
		List<DtoJoursFerieResponse> listeJourFerieDto = absences.stream().map(abs -> new DtoJoursFerieResponse(abs))
				.collect(Collectors.toList());

		return (absences.size() != 0) ? ResponseEntity.ok(listeJourFerieDto)
				: ResponseEntity.ok(new DtoAucuneAbsenceResponse("Aucun jours Fériés ou RTT employeur enregistrés"));
	}

	@PostMapping("create")
	public ResponseEntity<?> creerAbsence(@RequestBody @Valid DtoCreerAbsenceRequest dtoRequest, BindingResult resValid)
			throws CollegueIntrouvableException {

		if (!resValid.hasErrors()) {

			LocalDate dateDebutToLocalData = ConverterDate
					.convertDateToLocalDate(dtoRequest.getDatePremierJourAbsence());
			LocalDate dateFinToLocalData = ConverterDate.convertDateToLocalDate(dtoRequest.getDateDernierJourAbsence());
			Collegue collegueCreantAbsence = this.collegueService.recupererCollegue(dtoRequest.getIdCollegue());

			if (this.absenceService.controleChevaucheDate(dateDebutToLocalData, dateFinToLocalData,
					collegueCreantAbsence)) {

				Absence absence = this.absenceService.creerAbsence(new Absence(dateDebutToLocalData, dateFinToLocalData,
						ETypeJourAbsence.valueOf(dtoRequest.getTypeConge()), dtoRequest.getCommentaireAbsence(),
						EStatutDemandeAbsence.INITIALE, collegueCreantAbsence));

				return ResponseEntity.status(HttpStatus.OK).body(new DtoAbsenceResponse(absence));

			} else {
				String message = "L'abscence existe déjà aux dates :";
				return ResponseEntity.badRequest().body(new DtoAbsenceExistanteResponse(message,
						dtoRequest.getDateDernierJourAbsence(), dtoRequest.getDateDernierJourAbsence()));
			}

		} else {
			return ResponseEntity.badRequest().body("Problème survenu lors du Post");
		}
	}

	@PutMapping
	public ResponseEntity<?> editAbsence(@RequestBody DtoCreerAbsenceRequest dtoAbsenceRequest) {
		LocalDate dateDebutToLocalData = ConverterDate
				.convertDateToLocalDate(dtoAbsenceRequest.getDatePremierJourAbsence());
		LocalDate dateFinToLocalData = ConverterDate
				.convertDateToLocalDate(dtoAbsenceRequest.getDateDernierJourAbsence());
		Absence editAbsence = absenceService.updateAbsence(dtoAbsenceRequest.getIdCollegue(), dateDebutToLocalData,
				dateFinToLocalData, ETypeJourAbsence.valueOf(dtoAbsenceRequest.getTypeConge()),
				dtoAbsenceRequest.getCommentaireAbsence(),
				EStatutDemandeAbsence.valueOf(dtoAbsenceRequest.getStatutDemande()));
		return ResponseEntity.ok(new DtoAbsenceResponse(editAbsence));
	}

}
