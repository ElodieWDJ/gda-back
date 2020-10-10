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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.Absence;
import dev.domain.Collegue;
import dev.domain.EStatutDemandeAbsence;
import dev.domain.ETypeJourAbsence;
import dev.domain.dto.DtoAbsenceExistanteResponse;
import dev.domain.dto.DtoAbsenceResponse;
import dev.domain.dto.DtoAucuneAbsenceResponse;
import dev.domain.dto.DtoCreerAbsenceRequest;
import dev.domain.exceptions.CollegueIntrouvableException;
import dev.domain.services.AbsenceService;
import dev.domain.services.CollegueService;
import dev.utils.ConverterDate;

@RestController
@RequestMapping("gestion/collegue")
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
        List<DtoAbsenceResponse> listeAbsenceDto = new ArrayList<DtoAbsenceResponse>();
        for (Absence abs : absences) {
            listeAbsenceDto.add(new DtoAbsenceResponse(abs));
        }

        if (absences.size() != 0) {
            return ResponseEntity.ok(listeAbsenceDto);
        } else {
            return ResponseEntity.ok(new DtoAucuneAbsenceResponse("Aucune absence enregistrée"));
        }
    }

	@PostMapping("absence/create")
	public ResponseEntity<?> creerAbsence(@RequestBody @Valid DtoCreerAbsenceRequest dtoRequest, BindingResult resValid)
			throws CollegueIntrouvableException {
		if (!resValid.hasErrors()) {
				LocalDate dateDebutToLocalData = ConverterDate.convertDateToLocalDate(dtoRequest.getDateDebut());
				LocalDate dateFinToLocalData =  ConverterDate.convertDateToLocalDate(dtoRequest.getDateFin());
				Collegue collegueCreantAbsence = this.collegueService.recupererCollegue(dtoRequest.getIdUtilisateur());
				
				if(this.absenceService.controleChevaucheDate(dateDebutToLocalData, dateFinToLocalData, collegueCreantAbsence)) {
					
					Absence absence = this.absenceService.creerAbsence(new Absence(dateDebutToLocalData, dateFinToLocalData,
							ETypeJourAbsence.valueOf(dtoRequest.getTypeConge()), dtoRequest.getMotif(),
							EStatutDemandeAbsence.INITIALE, collegueCreantAbsence));
					
					return ResponseEntity.status(HttpStatus.OK).body("Absence insérée en BDD");
				
			} else {
				String message = "L'abscence existe déjà aux dates :";
				return ResponseEntity.badRequest().body(new DtoAbsenceExistanteResponse(message, dtoRequest.getDateDebut(), dtoRequest.getDateFin()));
			}
		} else {
			return ResponseEntity.badRequest().body("Problème survenu lors du Post");
		}
	}
}
