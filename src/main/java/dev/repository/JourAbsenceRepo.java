package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.entite.JourAbsence;

public interface JourAbsenceRepo extends JpaRepository<JourAbsence, Long> {

}
