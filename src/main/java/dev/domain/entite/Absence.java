package dev.domain.entite;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import dev.domain.enums.EStatutDemandeAbsence;
import dev.domain.enums.ETypeJourAbsence;

@Entity
public class Absence {

// ATTRIBUTS DE CLASSE ----------------------------------------------------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate datePremierJourAbsence;

	private LocalDate dateDernierJourAbsence;

	@Enumerated(EnumType.STRING)
	private ETypeJourAbsence typeConge;

	private String commentaireAbsence;

	@Enumerated(EnumType.STRING)
	private EStatutDemandeAbsence statutDemandeAbsence;

	@ManyToOne
	private Collegue collegue;

// CONSTRUCTEUR  ----------------------------------------------------------------------------------	

	public Absence() {

	}

	public Absence(LocalDate datePremierJourAbsence, LocalDate dateDernierJourAbsence, ETypeJourAbsence typeConge,
			String commentaireAbsence, EStatutDemandeAbsence statutDemandeAbsence, Collegue collegue) {
		this.datePremierJourAbsence = datePremierJourAbsence;
		this.dateDernierJourAbsence = dateDernierJourAbsence;
		this.typeConge = typeConge;
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

//	public List<JourAbsence> getListeJourAbsence() {
//		return listeJourAbsence;
//	}
//
//	public void setListeJourAbsence(List<JourAbsence> listeJourAbsence) {
//		this.listeJourAbsence = listeJourAbsence;
//	}

	public ETypeJourAbsence getTypeConge() {
		return typeConge;
	}

	public void setTypeConge(ETypeJourAbsence typeConge) {
		this.typeConge = typeConge;
	}

	public String getCommentaireAbsence() {
		return commentaireAbsence;
	}

	public void setCommentaireAbsence(String commentaireAbsence) {
		this.commentaireAbsence = commentaireAbsence;
	}

	public EStatutDemandeAbsence getStatutDemandeAbsence() {
		return statutDemandeAbsence;
	}

	public void setStatutDemandeAbsence(EStatutDemandeAbsence statutDemandeAbsence) {
		this.statutDemandeAbsence = statutDemandeAbsence;
	}

	public Collegue getCollegue() {
		return collegue;
	}

	public void setCollegue(Collegue collegue) {
		this.collegue = collegue;
	}

	@Override
	public String toString() {
		return "Absence [id=" + id + ", datePremierJourAbsence=" + datePremierJourAbsence + ", dateDernierJourAbsence="
				+ dateDernierJourAbsence + ", typeConge=" + typeConge + ", commentaireAbsence=" + commentaireAbsence
				+ ", statutDemandeAbsence=" + statutDemandeAbsence + ", collegue=" + collegue + "]";
	}

}
