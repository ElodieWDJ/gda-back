package dev.domain.entite;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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

	public Absence(Long idAbsence, LocalDate datePremierJourAbsence, LocalDate dateDernierJourAbsence,
			ETypeJourAbsence typeConge, String commentaireAbsence, EStatutDemandeAbsence statutDemandeAbsence,
			Collegue collegue) {
		this.datePremierJourAbsence = datePremierJourAbsence;
		this.dateDernierJourAbsence = dateDernierJourAbsence;
		this.typeConge = typeConge;
		this.commentaireAbsence = commentaireAbsence;
		this.statutDemandeAbsence = statutDemandeAbsence;
		this.collegue = collegue;
		this.id = idAbsence;
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

// FONCTIONS CUSTOM  ----------------------------------------------------------------------------------	

	public int getNbJoursAbsence() {

		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate dateDebut = this.getDatePremierJourAbsence();
		LocalDate dateFin = this.getDateDernierJourAbsence();
		long nbJourAbsence = 0;

		int retour = 0;

		// passage du LocalDate en Date
		Date date1 = Date.from(dateDebut.atStartOfDay(defaultZoneId).toInstant());
		Date date2 = Date.from(dateFin.atStartOfDay(defaultZoneId).toInstant());

		if (date1 != null && date2 != null) {
			final long MILLISECOND_PER_DAY = 1000 * 60 * 60 * 24;
			long difference = date2.getTime() - date1.getTime();

			nbJourAbsence = (difference / MILLISECOND_PER_DAY);
			retour = (int) Math.round(nbJourAbsence) + 1;
		}

		return retour;

		/*
		 * passage du long en Integer Long dateApresSoustraction =
		 * Math.abs(date2.getTime() - date1.getTime());
		 * System.out.println(dateApresSoustraction); // compte les bites int
		 * nbJoursAbsence = dateApresSoustraction.bitCount(dateApresSoustraction);
		 * System.out.println(nbJoursAbsence); // nbJourRestant =
		 * collegue.getSoldesVacances()-nbJourParAbsence;
		 */

		// return nbJoursAbsence;
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
