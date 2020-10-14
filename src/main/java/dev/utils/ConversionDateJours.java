package dev.utils;

import java.time.LocalDate;
import java.util.Calendar;

public class ConversionDateJours {

	public static String convertionDateJourSemaine(LocalDate date) {

		String jourEnString;

		// Retourne un integer du jours de la LocalDate
		int jourEnInt = date.getDayOfMonth();

		// Switch pour attibuer l'intéger au jours de la semaine en string
		switch (jourEnInt) {
		case Calendar.MONDAY:
			jourEnString = "Lundi";
			break;
		case Calendar.TUESDAY:
			jourEnString = "Mardi";
			break;
		case Calendar.WEDNESDAY:
			jourEnString = "Mercredi";
			break;
		case Calendar.THURSDAY:
			jourEnString = "Jeudi";
			break;
		case Calendar.FRIDAY:
			jourEnString = "Vendredi";
			break;
		case Calendar.SATURDAY:
			jourEnString = "Samedi";
			break;
		case Calendar.SUNDAY:
			jourEnString = "Dimanche";
			break;
		default:
			jourEnString = "Erreur de conversion";
			break;
		}
		return jourEnString;
	}

}
