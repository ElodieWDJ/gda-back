package dev.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.YearMonth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.services.AbsenceService;
import dev.domain.services.CollegueService;

@RestController
@RequestMapping("vueSynthethique") // http://localhost:4200/vueSynthethique
public class VueSynthetiqueController {

	private CollegueService collegueService;
	private AbsenceService absenceService;

	public VueSynthetiqueController(CollegueService collegueService, AbsenceService absenceService) {
		super();
		this.collegueService = collegueService;
		this.absenceService = absenceService;
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

}
