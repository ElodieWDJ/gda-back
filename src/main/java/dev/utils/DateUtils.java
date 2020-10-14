package dev.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.AbstractMap;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


import org.springframework.stereotype.Component;

import dev.domain.enums.EMois;

@Component
public class DateUtils {
	protected static Map<EMois, Integer> moisCalendarRelation = Map.ofEntries(
			new AbstractMap.SimpleEntry<EMois, Integer>(EMois.JANVIER, Calendar.JANUARY),
			new AbstractMap.SimpleEntry<EMois, Integer>(EMois.FEVRIER, Calendar.FEBRUARY),
			new AbstractMap.SimpleEntry<EMois, Integer>(EMois.MARS, Calendar.MARCH),
			new AbstractMap.SimpleEntry<EMois, Integer>(EMois.AVRIL, Calendar.APRIL),
			new AbstractMap.SimpleEntry<EMois, Integer>(EMois.MAI, Calendar.MAY),
			new AbstractMap.SimpleEntry<EMois, Integer>(EMois.JUIN, Calendar.JUNE),
			new AbstractMap.SimpleEntry<EMois, Integer>(EMois.JUILLET, Calendar.JULY),
			new AbstractMap.SimpleEntry<EMois, Integer>(EMois.AOUT, Calendar.AUGUST),
			new AbstractMap.SimpleEntry<EMois, Integer>(EMois.SEPTEMBRE, Calendar.SEPTEMBER),
			new AbstractMap.SimpleEntry<EMois, Integer>(EMois.OCTOBRE, Calendar.OCTOBER),
			new AbstractMap.SimpleEntry<EMois, Integer>(EMois.NOVEMBRE, Calendar.NOVEMBER),
			new AbstractMap.SimpleEntry<EMois, Integer>(EMois.DECEMBRE, Calendar.DECEMBER)
			
	);
	
	protected static Map<Integer, String> moisCalendarConversion = Map.ofEntries(
			new AbstractMap.SimpleEntry<Integer, String>(Calendar.JANUARY, "01"),
			new AbstractMap.SimpleEntry<Integer, String>(Calendar.FEBRUARY, "02"),
			new AbstractMap.SimpleEntry<Integer, String>(Calendar.MARCH, "03"),
			new AbstractMap.SimpleEntry<Integer, String>(Calendar.APRIL, "04"),
			new AbstractMap.SimpleEntry<Integer, String>(Calendar.MAY, "05"),
			new AbstractMap.SimpleEntry<Integer, String>(Calendar.JUNE, "06"),
			new AbstractMap.SimpleEntry<Integer, String>(Calendar.JULY, "07"),
			new AbstractMap.SimpleEntry<Integer, String>(Calendar.AUGUST, "08"),
			new AbstractMap.SimpleEntry<Integer, String>(Calendar.SEPTEMBER, "09"),
			new AbstractMap.SimpleEntry<Integer, String>(Calendar.OCTOBER, "10"),
			new AbstractMap.SimpleEntry<Integer, String>(Calendar.NOVEMBER, "11"),
			new AbstractMap.SimpleEntry<Integer, String>(Calendar.DECEMBER, "12")
			
	);
			
			
	public static LocalDate convertDateToLocalDate(Date date) {
		 return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}		
	
	public static LocalDate convertStringToLocalDate(String jour, String mois, String annee) {
		String moisEnChiffre = DateUtils.monthToConversion(mois);
		return LocalDate.parse(annee + "-" + moisEnChiffre + "-" + jour);
	}
	private static String monthToConversion(String mois) {
		Integer integerEnAnglais = DateUtils.moisCalendarRelation.get(EMois.valueOf(mois));
		return DateUtils.moisCalendarConversion.get(integerEnAnglais);
	}
	
	public Date getLastDayOfMonth(String annee, String mois) throws ParseException {
		String moisEnChiffre = DateUtils.monthToConversion(mois);
		String jourMaxDuMois = this.getNombreJourMaxParMois(Integer.parseInt(moisEnChiffre));
		if(jourMaxDuMois.length() > 2) {
			String jourMaxDuMoisFormat = jourMaxDuMois.subSequence(1, jourMaxDuMois.length()).toString();
			return new SimpleDateFormat("dd/MM/yyyy").parse(jourMaxDuMoisFormat + "/" + moisEnChiffre + "/" + annee);
		}else {
			return new SimpleDateFormat("dd/MM/yyyy").parse(jourMaxDuMois + "/" + moisEnChiffre + "/" + annee);
		}
	}
	
	private String getNombreJourMaxParMois(Integer mois) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH,  mois);
		return String.format("%03d", cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	}
}
