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

	private ETypeJourAbsence typeJourAbsence;

	private LocalDate dateDuJour;

	private String commentaire;

	public JourAbsence() {

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
