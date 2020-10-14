package dev.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

import dev.domain.dto.DtoAucuneAbsenceResponse;
import dev.domain.dto.DtoCreerAbsenceRequest;
import dev.domain.dto.DtoJoursFerieResponse;
import dev.domain.dto.DtoUpdateJourFerieRttRequest;
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
@RequestMapping("jourFerieRTT") // http://localhost:4200/absence
public class JourFerieRTTController {

	private CollegueService collegueService;
	private AbsenceService absenceService;

	public JourFerieRTTController(CollegueService collegueService, AbsenceService absenceService) {
		this.collegueService = collegueService;
		this.absenceService = absenceService;
	}

	/* ----------------- CREATION -------------------- */

	@PostMapping("create")
	public ResponseEntity<?> creerJourFerieRTT(@RequestBody @Valid DtoCreerAbsenceRequest dtoRequest,
			BindingResult resValid) throws CollegueIntrouvableException {

		if (!resValid.hasErrors()) {

			LocalDate dateDebutToLocalData = ConverterDate
					.convertDateToLocalDate(dtoRequest.getDatePremierJourAbsence());
			LocalDate dateFinToLocalData = ConverterDate.convertDateToLocalDate(dtoRequest.getDateDernierJourAbsence());

			List<Collegue> listeDeTousLesCollegues = collegueService.getAllCollegues();

			if (ETypeJourAbsence.valueOf(dtoRequest.getTypeConge()) == ETypeJourAbsence.JOUR_FERIE) {
				// On créer une absence de type jour Férie pour TOUS les collegues
				for (Collegue collegueTempo : listeDeTousLesCollegues) {
					Absence absence = this.absenceService.creerAbsence(new Absence(dateDebutToLocalData,
							dateFinToLocalData, ETypeJourAbsence.valueOf(dtoRequest.getTypeConge()),
							dtoRequest.getCommentaireAbsence(), EStatutDemandeAbsence.VALIDEE, collegueTempo));
				}
				return ResponseEntity.status(HttpStatus.OK).body("Le jour férié / RTT a bien été enregistré");

			} else if (ETypeJourAbsence.valueOf(dtoRequest.getTypeConge()) == ETypeJourAbsence.RTT_EMPLOYEUR) {
				// On créer une absence de type jour Férie pour TOUS les collegues
				for (Collegue collegueTempo : listeDeTousLesCollegues) {
					Absence absence = this.absenceService.creerAbsence(new Absence(dateDebutToLocalData,
							dateFinToLocalData, ETypeJourAbsence.valueOf(dtoRequest.getTypeConge()),
							dtoRequest.getCommentaireAbsence(), EStatutDemandeAbsence.INITIALE, collegueTempo));
				}

				return ResponseEntity.status(HttpStatus.OK).body("Le RTT a bien été enregistré");
			}

		}
		// On lit ce code uniquement si tout échoue avant donc quand resValideHasErrors
		String message = "Erreur dans la création de ce jour férié";
		return ResponseEntity.badRequest().body(message);
	}

	/* ----------------- MODIFIER -------------------- */

	@PutMapping("modifier")
	public ResponseEntity<?> editJourFerieRTT(@RequestBody DtoUpdateJourFerieRttRequest dtoRequest)
			throws CollegueIntrouvableException {

		LocalDate dateACherche = ConverterDate.convertDateToLocalDate(dtoRequest.getDatePremierJourAbsence());

		List<Absence> listAbsence = this.absenceService.getAllJourFerieRTTByDate(dateACherche);

		if (listAbsence.size() > 0) {
			for (Absence absFromListe : listAbsence) {

				Absence absUpdated = new Absence(absFromListe.getId(),
						ConverterDate.convertDateToLocalDate(dtoRequest.getDatePremierJourAbsence()),
						ConverterDate.convertDateToLocalDate(dtoRequest.getDatePremierJourAbsence()),
						ETypeJourAbsence.valueOf(dtoRequest.getTypeConge()), dtoRequest.getCommentaireAbsence(),
						absFromListe.getStatutDemandeAbsence(), absFromListe.getCollegue());

				Absence editAbsence = absenceService.updateAbsence(absUpdated);
			}
			return ResponseEntity.ok("RTT/Jour Férié mis à jour");
		} else {
			return ResponseEntity.ok(new AbsenceIntrouvableException("Ce jour férié/RTT n'existe pas"));
		}
	}

	/* ----------------- LISTER -------------------- */

	@GetMapping("joursferies/{annee}")
	public ResponseEntity<?> listerAllJoursFeriesEtRttEmployeur(@PathVariable Integer annee)
			throws AbsenceIntrouvableException {
		List<Absence> allAbsences = this.absenceService.getAllRttEtJoursFeriesParAnnee(annee);
		List<Absence> absencesFiltre = new ArrayList();

		// Filtrage des absences pour obtenir seulement les JF/RTT uniques, on filtre
		// par date qui sont order par ordre croissant
		for (Absence abs : allAbsences) {
			LocalDate dateTampon = LocalDate.of(2000, 1, 1); // Date par défaut , elle aurait pu être vide
			if (abs.getDatePremierJourAbsence() != dateTampon) {
				absencesFiltre.add(abs);
			}
		}

		List<DtoJoursFerieResponse> listeJourFerieDto = new ArrayList();
		// absencesFiltre.stream()
		// .map(abs -> new DtoJoursFerieResponse(abs)).collect(Collectors.toList());

		for (Absence abs : absencesFiltre) {
			listeJourFerieDto.add(new DtoJoursFerieResponse(abs));
		}

		return (absencesFiltre.size() != 0) ? ResponseEntity.ok(listeJourFerieDto)
				: ResponseEntity.ok(new DtoAucuneAbsenceResponse("Aucun jours Fériés ou RTT employeur enregistrés"));
	}
}
