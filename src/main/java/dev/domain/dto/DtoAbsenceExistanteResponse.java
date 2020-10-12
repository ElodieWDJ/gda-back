package dev.domain.dto;

import java.util.Date;

public class DtoAbsenceExistanteResponse {
	private String message;
	private Date dateDebut;
	private Date dateFin;
	
	public DtoAbsenceExistanteResponse(String message, Date dateDebut, Date dateFin) {
		this.message = message;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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
	
	
}
