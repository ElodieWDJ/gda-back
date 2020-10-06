package dev.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Absence {

// ATTRIBUTS DE CLASSE ----------------------------------------------------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate datePremierJourAbsence;

	private LocalDate dateDernierJourAbsence;

	@OneToMany
	private List<JourAbsence> listeJourAbsence;

	private String commentaireAbsence;

	private eStatutDemandeAbsence statutDemandeAbsence;

	@ManyToOne
	@JoinColumn(name = "COLLEGUE_ID")
	private Collegue collegue;

// CONSTRUCTEUR  ----------------------------------------------------------------------------------	

	public Absence() {

	}

// GETTERS AND SETTERS  ----------------------------------------------------------------------------------	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDatePremierJourAbsence() {
		return datePremierJourAbsence;
	}

	public void setDatePremierJourAbsence(LocalDate datePremierJourAbsence) {
		this.datePremierJourAbsence = datePremierJourAbsence;
	}

	public LocalDate getDateDernierJourAbsence() {
		return dateDernierJourAbsence;
	}

	public void setDateDernierJourAbsence(LocalDate dateDernierJourAbsence) {
		this.dateDernierJourAbsence = dateDernierJourAbsence;
	}

	public List<JourAbsence> getListeJourAbsence() {
		return listeJourAbsence;
	}

	public void setListeJourAbsence(List<JourAbsence> listeJourAbsence) {
		this.listeJourAbsence = listeJourAbsence;
	}

	public String getCommentaireAbsence() {
		return commentaireAbsence;
	}

	public void setCommentaireAbsence(String commentaireAbsence) {
		this.commentaireAbsence = commentaireAbsence;
	}

	public eStatutDemandeAbsence getStatutDemandeAbsence() {
		return statutDemandeAbsence;
	}

	public void setStatutDemandeAbsence(eStatutDemandeAbsence statutDemandeAbsence) {
		this.statutDemandeAbsence = statutDemandeAbsence;
	}

	public Collegue getCollegue() {
		return collegue;
	}

	public void setCollegue(Collegue collegue) {
		this.collegue = collegue;
	}

}
