package dev.domain.entite;

import java.time.LocalDate;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import dev.domain.enums.ETypeJourAbsence;

public class JourFerie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private LocalDate dateDuJour;

	/**
	 * @param id
	 * @param dateDuJour
	 * @param typeJourAbsence
	 * @param commentaire
	 * @param collegue
	 */
	public JourFerie(LocalDate dateDuJour, ETypeJourAbsence typeJourAbsence, String commentaire, Collegue collegue) {
		super();

		this.dateDuJour = dateDuJour;
		this.typeJourAbsence = typeJourAbsence;
		this.commentaire = commentaire;
		this.collegue = collegue;
	}

	@Enumerated(EnumType.STRING)
	private ETypeJourAbsence typeJourAbsence;
	private String commentaire;

	@ManyToOne
	private Collegue collegue;

	/**
	 * @return the collegue
	 */
	public Collegue getCollegue() {
		return collegue;
	}

	/**
	 * @param collegue the collegue to set
	 */
	public void setCollegue(Collegue collegue) {
		this.collegue = collegue;
	}

	public JourFerie() {

	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the dateDuJour
	 */
	public LocalDate getDateDuJour() {
		return dateDuJour;
	}

	/**
	 * @param dateDuJour the dateDuJour to set
	 */
	public void setDateDuJour(LocalDate dateDuJour) {
		this.dateDuJour = dateDuJour;
	}

	/**
	 * @return the typeJourAbsence
	 */
	public ETypeJourAbsence getTypeJourAbsence() {
		return typeJourAbsence;
	}

	/**
	 * @param typeJourAbsence the typeJourAbsence to set
	 */
	public void setTypeJourAbsence(ETypeJourAbsence typeJourAbsence) {
		this.typeJourAbsence = typeJourAbsence;
	}

	/**
	 * @return the commentaire
	 */
	public String getCommentaire() {
		return commentaire;
	}

	/**
	 * @param commentaire the commentaire to set
	 */
	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

}
