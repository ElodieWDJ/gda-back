package dev.domain.dto;

import java.util.Date;

import dev.domain.EStatutDemandeAbsence;
import dev.domain.ETypeJourAbsence;

public class DtoCreerAbsenceRequest {
	private Long idUtilisateur;
    private Date dateDebut;
    private Date dateFin;
    private String typeConge;
    private String motif;
    private String status;
    
	public DtoCreerAbsenceRequest(Long idUtilisateur, Date dateDebut, Date dateFin, String typeConge, String motif,
			String status) {
		this.idUtilisateur = idUtilisateur;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.typeConge = typeConge;
		this.motif = motif;
		this.status = status;
	}
	
	
	public Long getIdUtilisateur() {
		return idUtilisateur;
	}


	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}


	public Date getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public String getTypeConge() {
		return typeConge;
	}

	public void setTypeConge(String typeConge) {
		this.typeConge = typeConge;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    

}
