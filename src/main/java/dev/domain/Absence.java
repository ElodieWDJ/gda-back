package dev.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private List<JourAbsence> tabJourAbsence;

	private String commentaireAbsence;

	private eStatutDemandeAbsence statutDemandeAbsence;

	@ManyToOne
	private Collegue collegue;

// CONSTRUCTEUR  ----------------------------------------------------------------------------------	

	public Absence(Long id, LocalDate datePremierJourAbsence, LocalDate dateDernierJourAbsence,
			List<JourAbsence> tabJourAbsence, String commentaireAbsence, eStatutDemandeAbsence statutDemandeAbsence,
			Collegue collegue) {
		super();
		this.id = id;
		this.datePremierJourAbsence = datePremierJourAbsence;
		this.dateDernierJourAbsence = dateDernierJourAbsence;
		this.tabJourAbsence = tabJourAbsence;
		this.commentaireAbsence = commentaireAbsence;
		this.statutDemandeAbsence = statutDemandeAbsence;
		this.collegue = collegue;
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

	public List<JourAbsence> getTabJourAbsence() {
		return tabJourAbsence;
	}

	public void setTabJourAbsence(List<JourAbsence> tabJourAbsence) {
		this.tabJourAbsence = tabJourAbsence;
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
