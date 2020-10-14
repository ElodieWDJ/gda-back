package dev.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RestController;

import dev.domain.dto.DtoAbsenceExistanteResponse;
import dev.domain.dto.DtoAbsenceResponse;
import dev.domain.dto.DtoAbsenceResponseBis;
import dev.domain.dto.DtoAucuneAbsenceResponse;
import dev.domain.dto.DtoCreerAbsenceRequest;
import dev.domain.dto.DtoJoursFerieResponse;
import dev.domain.dto.DtoUpdateAbsenceRequest;
import dev.domain.dto.DtoUpdateAbsenceRequestBis;
import dev.domain.dto.ErrorRequestException;
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
	
	@GetMapping("liste/en-attente")
	public ResponseEntity<?> listeAbsenceEnAttente() {
		Optional<List<Absence>> absencesEnAttente = this.absenceService.getAllAbsenceEnAttente();
		if(absencesEnAttente.isPresent()) {
			List<Absence> absences = absencesEnAttente.get();
			List<DtoAbsenceResponseBis> response = absences.stream().map(absence -> new DtoAbsenceResponseBis(
																							absence.getId(), 
																							absence.getDatePremierJourAbsence(), 
																							absence.getDateDernierJourAbsence(), 
																							absence.getTypeConge().toString(), 
																							absence.getCollegue().getNom(), 
																							absence.getCollegue().getPrenom()))
																	.collect(Collectors.toList());

			return ResponseEntity.ok(response);
		} else {
			return ResponseEntity.badRequest().body("Aucune absences existantes");
		}
	}
	
	@PutMapping("valider")
	public ResponseEntity<?> valideAbsence(@RequestBody @Valid DtoUpdateAbsenceRequestBis dtoUpdateAbsence, BindingResult resValid) {
		if(!resValid.hasErrors()) {
			Optional<Absence> absence = this.absenceService.getById(dtoUpdateAbsence.getIdAbsence());
			if(absence.isPresent()) {
				Absence absenceRecuperee = absence.get();
				Absence absenceModifie = this.absenceService.updateAbsence(new Absence( absenceRecuperee.getId(),  
																						absenceRecuperee.getDatePremierJourAbsence(),
																						absenceRecuperee.getDateDernierJourAbsence(),
																						absenceRecuperee.getTypeConge(), 
																						absenceRecuperee.getCommentaireAbsence(), 
																						EStatutDemandeAbsence.VALIDEE, 
																						absenceRecuperee.getCollegue()));
				
				return ResponseEntity.ok(new DtoAbsenceResponse(absenceModifie));
				
			}else {
				return ResponseEntity.ok(new AbsenceIntrouvableException("Cette demande n'existe plus"));
			}
		} else {
			return ResponseEntity.badRequest().body("Une errreur est survenue");
		}
	}
	
	
	@PutMapping("rejeter")
	public ResponseEntity<?> rejeterAbsence(@RequestBody @Valid DtoUpdateAbsenceRequestBis dtoUpdateAbsence, BindingResult resValid) {
		if(!resValid.hasErrors()) {
			Optional<Absence> absence = this.absenceService.getById(dtoUpdateAbsence.getIdAbsence());
			if(absence.isPresent()) {
				Absence absenceRecuperee = absence.get();
				Absence absenceModifie = this.absenceService.updateAbsence(new Absence(absenceRecuperee.getId(),  
																						absenceRecuperee.getDatePremierJourAbsence(),
																						absenceRecuperee.getDateDernierJourAbsence(),
																						absenceRecuperee.getTypeConge(), 
																						absenceRecuperee.getCommentaireAbsence(), 
																						EStatutDemandeAbsence.REJETEE, 
																						absenceRecuperee.getCollegue()));
				return ResponseEntity.ok(new DtoAbsenceResponse(absenceModifie));
			}else {
				return ResponseEntity.ok(new AbsenceIntrouvableException("Cette demande n'existe plus"));
			}
		} else {
			return ResponseEntity.badRequest().body(new ErrorRequestException("Une erreur est survenue"));
		}
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
	public ResponseEntity<?> listerAllJoursFeriesEtRttEmployeur(@PathVariable Integer annee)
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

	@PutMapping("modifier")
	public ResponseEntity<?> editAbsence(@RequestBody DtoUpdateAbsenceRequest dtoRequest)
			throws CollegueIntrouvableException {
		Optional<Absence> absence = this.absenceService.getById(dtoRequest.getIdAbsence());
		
		if(absence.isPresent()) {
			Absence absenceRecuperee = absence.get();
			Absence absUpdated = new Absence(dtoRequest.getIdAbsence(), 
											ConverterDate.convertDateToLocalDate(dtoRequest.getDatePremierJourAbsence()), 
											ConverterDate.convertDateToLocalDate(dtoRequest.getDateDernierJourAbsence()),
											ETypeJourAbsence.valueOf(dtoRequest.getTypeConge()), 
											dtoRequest.getCommentaireAbsence(),
											EStatutDemandeAbsence.valueOf(dtoRequest.getStatutDemande()), 
											absenceRecuperee.getCollegue());
			
			Absence editAbsence = absenceService.updateAbsence(absUpdated);
			return ResponseEntity.ok(new DtoAbsenceResponse(editAbsence));
		} else {
			return ResponseEntity.ok(new AbsenceIntrouvableException("Cette Absence n'existe plus"));
		}
	}

}

