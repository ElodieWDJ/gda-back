package dev.repository;

import dev.domain.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CollegueRepo extends JpaRepository<Employe, Long> {

    Optional<Employe> findByEmail(String email);
}
