package dev.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.entite.Absence;

public interface AbsenceRepo extends JpaRepository<Absence, Long> {

	Optional<Absence> findByDatePremierJourAbsenceAndDateDernierJourAbsence(LocalDate datePremierJourAbsence, LocalDate dateDernierJourAbsence);

}
