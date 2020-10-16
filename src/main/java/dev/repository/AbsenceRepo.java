package dev.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.domain.entite.Absence;
import dev.domain.enums.EStatutDemandeAbsence;

public interface AbsenceRepo extends JpaRepository<Absence, Long> {

	Optional<Absence> findByDatePremierJourAbsenceAndDateDernierJourAbsence(LocalDate datePremierJourAbsence,
			LocalDate dateDernierJourAbsence);

	@Query("select a from Absence a where a.typeConge='RTT_EMPLOYEUR' OR a.typeConge='JOUR_FERIE' ORDER BY a.datePremierJourAbsence")
	Optional<List<Absence>> findAllJoursFerieEtRttEmployeur();

	Optional<List<Absence>> findByStatutDemandeAbsence(EStatutDemandeAbsence statutDemandeAbsence);

	@Query("select a from Absence a where a.typeConge='RTT_EMPLOYEUR' OR a.typeConge='JOUR_FERIE' AND a.datePremierJourAbsence=:dateParamRequete")
	List<Absence> findAbsenceJourFerieParDate(@Param("dateParamRequete") LocalDate dateDuJourFerie);

	@Query("select count (a) from Absence a where :dateParamRequete between a.datePremierJourAbsence and a.dateDernierJourAbsence ")
	int countAbsencesPourDateDonnee(@Param("dateParamRequete") LocalDate dateDuJourDuComptage);
}
