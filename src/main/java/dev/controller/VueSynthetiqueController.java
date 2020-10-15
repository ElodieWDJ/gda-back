package dev.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.domain.dto.DtoCalendrierSynthetiqueResponse;
import dev.domain.dto.DtoHistogrammeRequest;
import dev.domain.dto.ErrorRequestException;
import dev.domain.entite.Absence;
import dev.domain.exceptions.AbsenceIntrouvableException;
import dev.domain.services.AbsenceService;
import dev.domain.services.VueSynthetiqueService;
import dev.utils.DateUtils;

@RestController
@RequestMapping("vueSynthetique")
public class VueSynthetiqueController {
	private AbsenceService absenceService;
	private VueSynthetiqueService vueSynthetiqueService;
	
	public VueSynthetiqueController(AbsenceService absenceService, VueSynthetiqueService vueSynthetiqueService) {
		this.absenceService = absenceService;
		this.vueSynthetiqueService = vueSynthetiqueService;
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
