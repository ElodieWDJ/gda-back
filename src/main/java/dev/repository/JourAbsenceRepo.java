package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.JourAbsence;

public interface JourAbsenceRepo extends JpaRepository<JourAbsence, Long> {

}
