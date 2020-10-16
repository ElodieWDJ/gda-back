package dev.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.dto.DtoCalendrierSynthetiqueResponse;
import dev.domain.dto.DtoHistogrammeRequest;
import dev.domain.dto.ErrorRequestException;
import dev.domain.entite.Absence;
import dev.domain.exceptions.AbsenceIntrouvableException;
import dev.domain.services.AbsenceService;
import dev.domain.services.CollegueService;
import dev.domain.services.VueSynthetiqueService;
import dev.utils.DateUtils;

@RestController
@RequestMapping("vueSynthethique") // http://localhost:4200/vueSynthethique
public class VueSynthetiqueController {

	private CollegueService collegueService;
	private AbsenceService absenceService;
	private VueSynthetiqueService vueSynthetiqueService;
	public VueSynthetiqueController(CollegueService collegueService, AbsenceService absenceService, VueSynthetiqueService vueSynthetiqueService) {
		super();
		this.collegueService = collegueService;
		this.absenceService = absenceService;
		this.vueSynthetiqueService = vueSynthetiqueService;
	}

	/* ----------------- HISTOGRAMME -------------------- */

	@GetMapping("histogramme")
	public ResponseEntity<?> getHistogrammeAbsence(@RequestParam int mois, int annee) throws ParseException {

		// DateFormat format = new SimpleDateFormat("MMMM dd yyyy", Locale.FRENCH);
		// Date dateRecherche = format.parse(mois + " 01 " + annee); // On chercher à
		// obtenir le premier du mois par défaut

		YearMonth yearMonthObject = YearMonth.of(annee, mois);
		int daysInMonth = yearMonthObject.lengthOfMonth(); // Obtenir le nombre de jour du mois visé

		String[][] tableauAbsence = new String[daysInMonth][];

		for (int i = 0; i < daysInMonth; i++) {

			int jourDuMois = i + 1; // le premier jour du mois est le premier, donc +1 car i commence à 0

			LocalDate laDateDuJour = yearMonthObject.atDay(jourDuMois); // On obtient une local date à partir du jour,
																		// du mois et de l'année.

			String[] tabDansTab = new String[2];
			tabDansTab[0] = laDateDuJour.toString();
			tabDansTab[1] = Integer.toString(this.absenceService.compterLesAbsencesParDate(laDateDuJour));
			tableauAbsence[i] = tabDansTab;
			// On récupère un int représentant le nombre d'absences comptées pour ce jour
			// On stock ce int dans le tableau qui représente le mois et alimente le graph
			// de notre front
		}

		if (tableauAbsence[0] != null) {
			return ResponseEntity.status(HttpStatus.OK).body(tableauAbsence);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le comptage d'absence à échoué");
		}
	}
		@PostMapping("calendrier")
		public ResponseEntity<?> getCalendrierMoisSynthetique(@RequestBody @Valid DtoHistogrammeRequest dtoRequest, BindingResult resValid) throws ParseException {
			if(!resValid.hasErrors()) {
				Optional<List<Absence>> absenceValider = this.vueSynthetiqueService.listeAbsenceValideByInterval(dtoRequest.getMois(), dtoRequest.getAnnee());
				if(absenceValider.isPresent()) {
					String moisEnChiffre = DateUtils.monthToConversion(dtoRequest.getMois());
					Integer jourMaxDuMois = Integer.parseInt(DateUtils.getNombreJourMaxParMois(Integer.parseInt(moisEnChiffre)));
					List<DtoCalendrierSynthetiqueResponse> response = absenceValider.get().stream().map(absence -> new DtoCalendrierSynthetiqueResponse(absence, jourMaxDuMois)).collect(Collectors.toList());
					
					return ResponseEntity.ok(response);
				}else {
					return ResponseEntity.ok(new AbsenceIntrouvableException("Aucune absense trouvée"));
				}
			} else {
				return ResponseEntity.badRequest().body(new ErrorRequestException("Une erreur est survenue"));
			}
			
		}

	}

