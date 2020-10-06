package dev.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class JourAbsence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private eTypeJourAbsence typeJourAbsence;

	private LocalDate dateDuJour;

	private String commentaire;
}
