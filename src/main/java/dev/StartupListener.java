package dev;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.domain.entite.Absence;
import dev.domain.entite.Collegue;
import dev.domain.entite.ERole;
import dev.domain.entite.JourAbsence;
import dev.domain.entite.RoleCollegue;
import dev.domain.entite.Version;
import dev.domain.enums.EStatutDemandeAbsence;
import dev.domain.enums.ETypeJourAbsence;
import dev.repository.AbsenceRepo;
import dev.repository.CollegueRepo;
import dev.repository.JourAbsenceRepo;
import dev.repository.VersionRepo;

/**
 * Code de démarrage de l'application. Insertion de jeux de données.
 */
@Component
public class StartupListener {

	private String appVersion;
	private VersionRepo versionRepo;
	private PasswordEncoder passwordEncoder;
	private CollegueRepo collegueRepo;
	private AbsenceRepo absenceRepo;
	private JourAbsenceRepo jourAbsenceRepo;

	public StartupListener(@Value("${app.version}") String appVersion, VersionRepo versionRepo,
			PasswordEncoder passwordEncoder, CollegueRepo collegueRepo, AbsenceRepo absenceRepo,
			JourAbsenceRepo jourAbsenceRepo) {
		this.appVersion = appVersion;
		this.versionRepo = versionRepo;
		this.passwordEncoder = passwordEncoder;
		this.collegueRepo = collegueRepo;
		this.absenceRepo = absenceRepo;
		this.jourAbsenceRepo = jourAbsenceRepo;

	}

	@EventListener(ContextRefreshedEvent.class)
	public void onStart() {
		this.versionRepo.save(new Version(appVersion));

		// Création de deux utilisateurs

		Collegue col1 = new Collegue();
		col1.setNom("Admin");
		col1.setPrenom("DEV");
		col1.setEmail("admin@dev.fr");
		col1.setMotDePasse(passwordEncoder.encode("superpass"));
		col1.setRoles(Arrays.asList(new RoleCollegue(col1, ERole.ROLE_ADMINISTRATEUR),
				new RoleCollegue(col1, ERole.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col1);

		Collegue col2 = new Collegue();
		col2.setNom("User");
		col2.setPrenom("DEV");
		col2.setEmail("user@dev.fr");
		col2.setMotDePasse(passwordEncoder.encode("superpass"));
		col2.setRoles(Arrays.asList(new RoleCollegue(col2, ERole.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col2);

		Collegue col3 = new Collegue();
		col3.setNom("Bob");
		col3.setPrenom("Eponge");
		col3.setEmail("bobeponge@superApp.com");
		col3.setMotDePasse(passwordEncoder.encode("superpass"));
		col3.setRoles(Arrays.asList(new RoleCollegue(col3, ERole.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col3);

		Collegue col4 = new Collegue();
		col4.setNom("Boss");
		col4.setPrenom("Hugo");
		col4.setEmail("hugoboss@superApp.com");
		col4.setMotDePasse(passwordEncoder.encode("superpass"));
		col4.setRoles(Arrays.asList(new RoleCollegue(col4, ERole.ROLE_UTILISATEUR),
				new RoleCollegue(col4, ERole.ROLE_MANAGER)));
		this.collegueRepo.save(col4);

		Collegue col5 = new Collegue();
		col5.setNom("Gaston");
		col5.setPrenom("Hugo");
		col5.setEmail("hugogaston@superApp.com");
		col5.setMotDePasse(passwordEncoder.encode("superpass"));
		col5.setRoles(Arrays.asList(new RoleCollegue(col5, ERole.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col5);

		// Test Absence
		JourAbsence j1 = new JourAbsence();
		j1.setTypeJourAbsence(ETypeJourAbsence.RTT_EMPLOYEUR);
		j1.setDateDuJour(LocalDate.of(2020, Month.JANUARY, 2));
		j1.setCommentaire("Repos après les fêtes");
		this.jourAbsenceRepo.save(j1);

		JourAbsence j2 = new JourAbsence();
		j2.setTypeJourAbsence(ETypeJourAbsence.RTT_EMPLOYEUR);
		j2.setDateDuJour(LocalDate.of(2020, Month.JANUARY, 2));
		j2.setCommentaire("Test");
		this.jourAbsenceRepo.save(j2);

		JourAbsence j3 = new JourAbsence();
		j3.setTypeJourAbsence(ETypeJourAbsence.JOUR_FERIE);
		j3.setDateDuJour(LocalDate.of(2021, Month.JANUARY, 1));
		j3.setCommentaire("Jour de l'An");
		this.jourAbsenceRepo.save(j3);

		JourAbsence j4 = new JourAbsence();
		j4.setTypeJourAbsence(ETypeJourAbsence.JOUR_FERIE);
		j4.setDateDuJour(LocalDate.of(2021, Month.APRIL, 5));
		j4.setCommentaire("Pâques");
		this.jourAbsenceRepo.save(j4);

		JourAbsence j5 = new JourAbsence();
		j5.setTypeJourAbsence(ETypeJourAbsence.JOUR_FERIE);
		j5.setDateDuJour(LocalDate.of(2021, Month.MAY, 1));
		j5.setCommentaire("Fête du travail");
		this.jourAbsenceRepo.save(j5);

		JourAbsence j6 = new JourAbsence();
		j6.setTypeJourAbsence(ETypeJourAbsence.JOUR_FERIE);
		j6.setDateDuJour(LocalDate.of(2021, Month.MAY, 8));
		j6.setCommentaire("Victoire des Alliés en 1945");
		this.jourAbsenceRepo.save(j6);

		JourAbsence j7 = new JourAbsence();
		j7.setTypeJourAbsence(ETypeJourAbsence.JOUR_FERIE);
		j7.setDateDuJour(LocalDate.of(2021, Month.MAY, 13));
		j7.setCommentaire("Ascension");
		this.jourAbsenceRepo.save(j7);

		JourAbsence j8 = new JourAbsence();
		j8.setTypeJourAbsence(ETypeJourAbsence.JOUR_FERIE);
		j8.setDateDuJour(LocalDate.of(2021, Month.MAY, 24));
		j8.setCommentaire("Pentecôte");
		this.jourAbsenceRepo.save(j8);

		JourAbsence j9 = new JourAbsence();
		j9.setTypeJourAbsence(ETypeJourAbsence.JOUR_FERIE);
		j9.setDateDuJour(LocalDate.of(2021, Month.JULY, 14));
		j9.setCommentaire("Fête nationale");
		this.jourAbsenceRepo.save(j9);

		JourAbsence j10 = new JourAbsence();
		j10.setTypeJourAbsence(ETypeJourAbsence.JOUR_FERIE);
		j10.setDateDuJour(LocalDate.of(2021, Month.AUGUST, 15));
		j10.setCommentaire("Assomption");
		this.jourAbsenceRepo.save(j10);

		JourAbsence j11 = new JourAbsence();
		j11.setTypeJourAbsence(ETypeJourAbsence.RTT_EMPLOYEUR);
		j11.setDateDuJour(LocalDate.of(2021, Month.SEPTEMBER, 6));
		j11.setCommentaire("Rentré scolaire");
		this.jourAbsenceRepo.save(j11);

		JourAbsence j12 = new JourAbsence();
		j12.setTypeJourAbsence(ETypeJourAbsence.RTT_EMPLOYEUR);
		j12.setDateDuJour(LocalDate.of(2021, Month.SEPTEMBER, 20));
		j12.setCommentaire("Repos2");
		this.jourAbsenceRepo.save(j12);

		JourAbsence j13 = new JourAbsence();
		j13.setTypeJourAbsence(ETypeJourAbsence.RTT_EMPLOYEUR);
		j13.setDateDuJour(LocalDate.of(2021, Month.OCTOBER, 6));
		j13.setCommentaire("Champignon");
		this.jourAbsenceRepo.save(j13);

		JourAbsence j14 = new JourAbsence();
		j14.setTypeJourAbsence(ETypeJourAbsence.RTT_EMPLOYEUR);
		j14.setDateDuJour(LocalDate.of(2021, Month.OCTOBER, 6));
		j14.setCommentaire("Fermeture entreprise");
		this.jourAbsenceRepo.save(j14);

		JourAbsence j15 = new JourAbsence();
		j15.setTypeJourAbsence(ETypeJourAbsence.RTT_EMPLOYEUR);
		j15.setDateDuJour(LocalDate.of(2021, Month.OCTOBER, 22));
		j15.setCommentaire("Repos");
		this.jourAbsenceRepo.save(j15);

		JourAbsence j16 = new JourAbsence();
		j16.setTypeJourAbsence(ETypeJourAbsence.RTT_EMPLOYEUR);
		j16.setDateDuJour(LocalDate.of(2021, Month.NOVEMBER, 12));
		j16.setCommentaire("Fermeture entreprise");
		this.jourAbsenceRepo.save(j16);

		JourAbsence j17 = new JourAbsence();
		j17.setTypeJourAbsence(ETypeJourAbsence.JOUR_FERIE);
		j17.setDateDuJour(LocalDate.of(2021, Month.NOVEMBER, 11));
		j17.setCommentaire("Armistice");
		this.jourAbsenceRepo.save(j17);

		JourAbsence j18 = new JourAbsence();
		j18.setTypeJourAbsence(ETypeJourAbsence.JOUR_FERIE);
		j18.setDateDuJour(LocalDate.of(2021, Month.DECEMBER, 25));
		j18.setCommentaire("Noël");
		this.jourAbsenceRepo.save(j18);

		JourAbsence j19 = new JourAbsence();
		j19.setTypeJourAbsence(ETypeJourAbsence.RTT_EMPLOYEUR);
		j19.setDateDuJour(LocalDate.of(2021, Month.DECEMBER, 26));
		j19.setCommentaire("Repos après Noël");
		this.jourAbsenceRepo.save(j19);

		JourAbsence j20 = new JourAbsence();
		j20.setTypeJourAbsence(ETypeJourAbsence.RTT_EMPLOYEUR);
		j20.setDateDuJour(LocalDate.of(2021, Month.APRIL, 23));
		j20.setCommentaire("Jours de repos");
		this.jourAbsenceRepo.save(j20);

		JourAbsence j21 = new JourAbsence();
		j21.setTypeJourAbsence(ETypeJourAbsence.RTT_EMPLOYEUR);
		j21.setDateDuJour(LocalDate.of(2021, Month.NOVEMBER, 1));
		j21.setCommentaire("La Toussaint");
		this.jourAbsenceRepo.save(j21);

		JourAbsence j22 = new JourAbsence();
		j22.setTypeJourAbsence(ETypeJourAbsence.RTT_EMPLOYEUR);
		j22.setDateDuJour(LocalDate.of(2021, Month.MARCH, 12));
		j22.setCommentaire("Jours des salariés");
		this.jourAbsenceRepo.save(j22);

		List<JourAbsence> listeTest = new ArrayList<JourAbsence>();
		listeTest.add(j1);
		listeTest.add(j2);
		listeTest.add(j3);
		listeTest.add(j4);
		listeTest.add(j5);
		listeTest.add(j6);
		listeTest.add(j7);
		listeTest.add(j8);
		listeTest.add(j9);
		listeTest.add(j10);
		listeTest.add(j11);
		listeTest.add(j12);
		listeTest.add(j13);
		listeTest.add(j14);
		listeTest.add(j15);
		listeTest.add(j16);
		listeTest.add(j17);
		listeTest.add(j18);
		listeTest.add(j19);
		listeTest.add(j20);
		listeTest.add(j21);
		listeTest.add(j22);

		Absence absence1 = new Absence();
		absence1.setDatePremierJourAbsence(LocalDate.of(2021, Month.JANUARY, 1));
		absence1.setDateDernierJourAbsence(LocalDate.of(2021, Month.JANUARY, 2));
		absence1.setTypeConge(ETypeJourAbsence.CONGE_PAYE);
		// absence1.setListeJourAbsence(listeTest);
		absence1.setCommentaireAbsence("Vacance Noël");
		absence1.setStatutDemandeAbsence(EStatutDemandeAbsence.EN_ATTENTE_VALIDATION);
		absence1.setCollegue(col5);
		this.absenceRepo.save(absence1);

		Absence absence2 = new Absence();
		absence2.setDatePremierJourAbsence(LocalDate.of(2021, Month.MAY, 1));
		absence2.setDateDernierJourAbsence(LocalDate.of(2021, Month.MAY, 2));
		absence2.setTypeConge(ETypeJourAbsence.RTT);
		// absence1.setListeJourAbsence(listeTest);
		absence2.setCommentaireAbsence("Week end");
		absence2.setStatutDemandeAbsence(EStatutDemandeAbsence.EN_ATTENTE_VALIDATION);
		absence2.setCollegue(col5);
		this.absenceRepo.save(absence2);

		Absence absence3 = new Absence();
		absence3.setDatePremierJourAbsence(LocalDate.of(2021, Month.FEBRUARY, 1));
		absence3.setDateDernierJourAbsence(LocalDate.of(2021, Month.FEBRUARY, 2));
		absence3.setTypeConge(ETypeJourAbsence.RTT);
		absence3.setCommentaireAbsence("Recupération");
		absence3.setStatutDemandeAbsence(EStatutDemandeAbsence.EN_ATTENTE_VALIDATION);
		absence3.setCollegue(col5);
		this.absenceRepo.save(absence3);

		Absence absence4 = new Absence();
		absence4.setDatePremierJourAbsence(LocalDate.of(2021, Month.FEBRUARY, 1));
		absence4.setDateDernierJourAbsence(LocalDate.of(2021, Month.FEBRUARY, 2));
		absence4.setTypeConge(ETypeJourAbsence.RTT);
		absence4.setCommentaireAbsence("Week end");
		absence4.setStatutDemandeAbsence(EStatutDemandeAbsence.INITIALE);
		absence4.setCollegue(col5);
		this.absenceRepo.save(absence4);

		Absence absence5 = new Absence();
		absence5.setDatePremierJourAbsence(LocalDate.of(2021, Month.JANUARY, 25));
		absence5.setDateDernierJourAbsence(LocalDate.of(2021, Month.JANUARY, 31));
		absence5.setTypeConge(ETypeJourAbsence.CONGE_SANS_SOLDE);
		absence5.setCommentaireAbsence("Depart au ski");
		absence5.setStatutDemandeAbsence(EStatutDemandeAbsence.INITIALE);
		absence5.setCollegue(col5);
		this.absenceRepo.save(absence5);

		Absence absence6 = new Absence();
		absence6.setDatePremierJourAbsence(LocalDate.of(2021, Month.JANUARY, 23));
		absence6.setDateDernierJourAbsence(LocalDate.of(2021, Month.JANUARY, 30));
		absence6.setTypeConge(ETypeJourAbsence.CONGE_SANS_SOLDE);
		absence6.setCommentaireAbsence("Depart au ski");
		absence6.setStatutDemandeAbsence(EStatutDemandeAbsence.VALIDEE);
		absence6.setCollegue(col1);
		this.absenceRepo.save(absence6);

		Absence absence7 = new Absence();
		absence7.setDatePremierJourAbsence(LocalDate.of(2021, Month.FEBRUARY, 23));
		absence7.setDateDernierJourAbsence(LocalDate.of(2021, Month.FEBRUARY, 27));
		absence7.setTypeConge(ETypeJourAbsence.RTT);
		// absence1.setListeJourAbsence(listeTest);
		absence7.setCommentaireAbsence("Depart au ski");
		absence7.setStatutDemandeAbsence(EStatutDemandeAbsence.VALIDEE);
		absence7.setCollegue(col1);
		this.absenceRepo.save(absence7);

		Absence absence8 = new Absence();
		absence8.setDatePremierJourAbsence(LocalDate.of(2021, Month.MARCH, 10));
		absence8.setDateDernierJourAbsence(LocalDate.of(2021, Month.MARCH, 12));
		absence8.setTypeConge(ETypeJourAbsence.CONGE_PAYE);
		// absence1.setListeJourAbsence(listeTest);
		absence8.setCommentaireAbsence("Repos mérité");
		absence8.setStatutDemandeAbsence(EStatutDemandeAbsence.VALIDEE);
		absence8.setCollegue(col1);
		this.absenceRepo.save(absence8);

		Absence absence9 = new Absence();
		absence9.setDatePremierJourAbsence(LocalDate.of(2021, Month.APRIL, 20));
		absence9.setDateDernierJourAbsence(LocalDate.of(2021, Month.APRIL, 23));
		absence9.setTypeConge(ETypeJourAbsence.CONGE_PAYE);
		absence9.setCommentaireAbsence("Repos");
		absence9.setStatutDemandeAbsence(EStatutDemandeAbsence.REJETEE);
		absence9.setCollegue(col1);
		this.absenceRepo.save(absence9);

		Absence absence10 = new Absence();
		absence10.setDatePremierJourAbsence(LocalDate.of(2021, Month.MAY, 12));
		absence10.setDateDernierJourAbsence(LocalDate.of(2021, Month.MAY, 13));
		absence10.setTypeConge(ETypeJourAbsence.CONGE_SANS_SOLDE);
		absence10.setCommentaireAbsence("Repos");
		absence10.setStatutDemandeAbsence(EStatutDemandeAbsence.VALIDEE);
		absence10.setCollegue(col1);
		this.absenceRepo.save(absence10);

		Absence absence11 = new Absence();
		absence11.setDatePremierJourAbsence(LocalDate.of(2021, Month.AUGUST, 23));
		absence11.setDateDernierJourAbsence(LocalDate.of(2021, Month.AUGUST, 30));
		absence11.setTypeConge(ETypeJourAbsence.CONGE_SANS_SOLDE);
		absence11.setCommentaireAbsence("Vacance d'été");
		absence11.setStatutDemandeAbsence(EStatutDemandeAbsence.VALIDEE);
		absence11.setCollegue(col2);
		this.absenceRepo.save(absence11);

		Absence absence12 = new Absence();
		absence12.setDatePremierJourAbsence(LocalDate.of(2021, Month.MAY, 20));
		absence12.setDateDernierJourAbsence(LocalDate.of(2021, Month.MAY, 21));
		absence12.setTypeConge(ETypeJourAbsence.RTT);
		absence12.setCommentaireAbsence("Depart au ski");
		absence12.setStatutDemandeAbsence(EStatutDemandeAbsence.EN_ATTENTE_VALIDATION);
		absence12.setCollegue(col2);
		this.absenceRepo.save(absence7);

		Absence absence13 = new Absence();
		absence13.setDatePremierJourAbsence(LocalDate.of(2021, Month.MARCH, 15));
		absence13.setDateDernierJourAbsence(LocalDate.of(2021, Month.MARCH, 18));
		absence13.setTypeConge(ETypeJourAbsence.CONGE_PAYE);
		absence13.setCommentaireAbsence("Repos");
		absence13.setStatutDemandeAbsence(EStatutDemandeAbsence.VALIDEE);
		absence13.setCollegue(col2);
		this.absenceRepo.save(absence13);

		Absence absence14 = new Absence();
		absence14.setDatePremierJourAbsence(LocalDate.of(2021, Month.JUNE, 24));
		absence14.setDateDernierJourAbsence(LocalDate.of(2021, Month.JUNE, 26));
		absence14.setTypeConge(ETypeJourAbsence.CONGE_PAYE);
		absence14.setCommentaireAbsence("Congés");
		absence14.setStatutDemandeAbsence(EStatutDemandeAbsence.VALIDEE);
		absence14.setCollegue(col2);
		this.absenceRepo.save(absence14);

		Absence absence15 = new Absence();
		absence15.setDatePremierJourAbsence(LocalDate.of(2021, Month.MAY, 15));
		absence15.setDateDernierJourAbsence(LocalDate.of(2021, Month.MAY, 18));
		absence15.setTypeConge(ETypeJourAbsence.CONGE_SANS_SOLDE);
		absence15.setCommentaireAbsence("Repos");
		absence15.setStatutDemandeAbsence(EStatutDemandeAbsence.INITIALE);
		absence15.setCollegue(col2);
		this.absenceRepo.save(absence15);

		Absence absence16 = new Absence();
		absence16.setDatePremierJourAbsence(LocalDate.of(2021, Month.MAY, 14));
		absence16.setDateDernierJourAbsence(LocalDate.of(2021, Month.MAY, 15));
		absence16.setTypeConge(ETypeJourAbsence.CONGE_SANS_SOLDE);
		absence16.setCommentaireAbsence("Pont");
		absence16.setStatutDemandeAbsence(EStatutDemandeAbsence.VALIDEE);
		absence16.setCollegue(col3);
		this.absenceRepo.save(absence16);

		Absence absence17 = new Absence();
		absence17.setDatePremierJourAbsence(LocalDate.of(2021, Month.AUGUST, 20));
		absence17.setDateDernierJourAbsence(LocalDate.of(2021, Month.AUGUST, 25));
		absence17.setTypeConge(ETypeJourAbsence.CONGE_SANS_SOLDE);
		absence17.setCommentaireAbsence("Vacance d'été");
		absence17.setStatutDemandeAbsence(EStatutDemandeAbsence.VALIDEE);
		absence17.setCollegue(col3);
		this.absenceRepo.save(absence17);

		Absence absence18 = new Absence();
		absence18.setDatePremierJourAbsence(LocalDate.of(2021, Month.DECEMBER, 15));
		absence18.setDateDernierJourAbsence(LocalDate.of(2021, Month.DECEMBER, 19));
		absence18.setTypeConge(ETypeJourAbsence.RTT);
		absence18.setCommentaireAbsence("Depart au ski");
		absence18.setStatutDemandeAbsence(EStatutDemandeAbsence.VALIDEE);
		absence18.setCollegue(col3);
		this.absenceRepo.save(absence18);

		Absence absence19 = new Absence();
		absence19.setDatePremierJourAbsence(LocalDate.of(2021, Month.MARCH, 11));
		absence19.setDateDernierJourAbsence(LocalDate.of(2021, Month.MARCH, 15));
		absence19.setTypeConge(ETypeJourAbsence.CONGE_PAYE);
		absence19.setCommentaireAbsence("Congé");
		absence19.setStatutDemandeAbsence(EStatutDemandeAbsence.VALIDEE);
		absence19.setCollegue(col3);
		this.absenceRepo.save(absence19);

		Absence absence20 = new Absence();
		absence20.setDatePremierJourAbsence(LocalDate.of(2021, Month.JUNE, 10));
		absence20.setDateDernierJourAbsence(LocalDate.of(2021, Month.JUNE, 15));
		absence20.setTypeConge(ETypeJourAbsence.CONGE_PAYE);
		absence20.setCommentaireAbsence("Congés");
		absence20.setStatutDemandeAbsence(EStatutDemandeAbsence.VALIDEE);
		absence20.setCollegue(col3);
		this.absenceRepo.save(absence20);

		Absence absence21 = new Absence();
		absence21.setDatePremierJourAbsence(LocalDate.of(2021, Month.MAY, 15));
		absence21.setDateDernierJourAbsence(LocalDate.of(2021, Month.MAY, 18));
		absence21.setTypeConge(ETypeJourAbsence.CONGE_SANS_SOLDE);
		absence21.setCommentaireAbsence("Repos");
		absence21.setStatutDemandeAbsence(EStatutDemandeAbsence.VALIDEE);
		absence21.setCollegue(col4);
		this.absenceRepo.save(absence21);

		Absence absence22 = new Absence();
		absence22.setDatePremierJourAbsence(LocalDate.of(2021, Month.MAY, 10));
		absence22.setDateDernierJourAbsence(LocalDate.of(2021, Month.MAY, 14));
		absence22.setTypeConge(ETypeJourAbsence.CONGE_SANS_SOLDE);
		absence22.setCommentaireAbsence("Repos");
		absence22.setStatutDemandeAbsence(EStatutDemandeAbsence.VALIDEE);
		absence22.setCollegue(col4);
		this.absenceRepo.save(absence22);

		Absence absence23 = new Absence();
		absence23.setDatePremierJourAbsence(LocalDate.of(2021, Month.MAY, 20));
		absence23.setDateDernierJourAbsence(LocalDate.of(2021, Month.MAY, 21));
		absence23.setTypeConge(ETypeJourAbsence.CONGE_SANS_SOLDE);
		absence23.setCommentaireAbsence("Repos");
		absence23.setStatutDemandeAbsence(EStatutDemandeAbsence.VALIDEE);
		absence23.setCollegue(col4);
		this.absenceRepo.save(absence23);

		Absence absence24 = new Absence();
		absence24.setDatePremierJourAbsence(LocalDate.of(2021, Month.MAY, 26));
		absence24.setDateDernierJourAbsence(LocalDate.of(2021, Month.MAY, 27));
		absence24.setTypeConge(ETypeJourAbsence.CONGE_SANS_SOLDE);
		absence24.setCommentaireAbsence("Repos");
		absence24.setStatutDemandeAbsence(EStatutDemandeAbsence.VALIDEE);
		absence24.setCollegue(col4);
		this.absenceRepo.save(absence24);

		Absence absence25 = new Absence();
		absence25.setDatePremierJourAbsence(LocalDate.of(2021, Month.APRIL, 24));
		absence25.setDateDernierJourAbsence(LocalDate.of(2021, Month.APRIL, 23));
		absence25.setTypeConge(ETypeJourAbsence.CONGE_SANS_SOLDE);
		absence25.setCommentaireAbsence("Repos");
		absence25.setStatutDemandeAbsence(EStatutDemandeAbsence.VALIDEE);
		absence25.setCollegue(col4);
		this.absenceRepo.save(absence25);

		Absence absence26 = new Absence();
		absence26.setDatePremierJourAbsence(LocalDate.of(2021, Month.MAY, 1));
		absence26.setDateDernierJourAbsence(LocalDate.of(2021, Month.MAY, 2));
		absence26.setTypeConge(ETypeJourAbsence.CONGE_PAYE);
		absence26.setCommentaireAbsence("Congé");
		absence26.setStatutDemandeAbsence(EStatutDemandeAbsence.VALIDEE);
		absence26.setCollegue(col3);
		this.absenceRepo.save(absence26);

		Absence absence27 = new Absence();
		absence27.setDatePremierJourAbsence(LocalDate.of(2021, Month.MAY, 3));
		absence27.setDateDernierJourAbsence(LocalDate.of(2021, Month.MAY, 4));
		absence27.setTypeConge(ETypeJourAbsence.CONGE_PAYE);
		absence27.setCommentaireAbsence("Congé");
		absence27.setStatutDemandeAbsence(EStatutDemandeAbsence.EN_ATTENTE_VALIDATION);
		absence27.setCollegue(col2);
		this.absenceRepo.save(absence27);

		Absence absence28 = new Absence();
		absence28.setDatePremierJourAbsence(LocalDate.of(2021, Month.MAY, 27));
		absence28.setDateDernierJourAbsence(LocalDate.of(2021, Month.MAY, 28));
		absence28.setTypeConge(ETypeJourAbsence.CONGE_PAYE);
		absence28.setCommentaireAbsence("Congé");
		absence28.setStatutDemandeAbsence(EStatutDemandeAbsence.VALIDEE);
		absence28.setCollegue(col1);
		this.absenceRepo.save(absence28);

		Absence absence29 = new Absence();
		absence29.setDatePremierJourAbsence(LocalDate.of(2021, Month.MAY, 6));
		absence29.setDateDernierJourAbsence(LocalDate.of(2021, Month.MAY, 7));
		absence29.setTypeConge(ETypeJourAbsence.CONGE_PAYE);
		absence29.setCommentaireAbsence("Congé");
		absence29.setStatutDemandeAbsence(EStatutDemandeAbsence.VALIDEE);
		absence29.setCollegue(col5);
		this.absenceRepo.save(absence29);

		Absence absence30 = new Absence();
		absence30.setDatePremierJourAbsence(LocalDate.of(2021, Month.MAY, 8));
		absence30.setDateDernierJourAbsence(LocalDate.of(2021, Month.MAY, 9));
		absence30.setTypeConge(ETypeJourAbsence.CONGE_PAYE);
		absence30.setCommentaireAbsence("Congé");
		absence30.setStatutDemandeAbsence(EStatutDemandeAbsence.VALIDEE);
		absence30.setCollegue(col4);
		this.absenceRepo.save(absence30);

		List<Absence> listeAbsence = new ArrayList<Absence>();
		listeAbsence.add(absence1);
		listeAbsence.add(absence2);
		listeAbsence.add(absence3);
		listeAbsence.add(absence4);
		listeAbsence.add(absence5);

		col5.setListeAbsencesDuCollegue(listeAbsence);
		this.collegueRepo.save(col5);
	}

}
