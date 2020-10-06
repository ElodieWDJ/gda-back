package dev.domain;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Absence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate datePremierJourAbsence;

	private LocalDate dateDernierJourAbsence;

	private ArrayList<JourAbsence> tabJourAbsence;

	private String commentaireAbsence;

	private eStatutDemandeAbsence statutDemandeAbsence;

	@ManyToOne
	private Collegue collegue;

}
