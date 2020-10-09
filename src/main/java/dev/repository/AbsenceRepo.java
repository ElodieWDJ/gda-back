package dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.domain.entite.Absence;

public interface AbsenceRepo extends JpaRepository<Absence, Long> {

}
