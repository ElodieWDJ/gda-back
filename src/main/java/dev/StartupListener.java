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
import dev.domain.entite.EStatutDemandeAbsence;
import dev.domain.entite.ETypeJourAbsence;
import dev.domain.entite.JourAbsence;
import dev.domain.entite.RoleCollegue;
import dev.domain.entite.Version;
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
		j1.setTypeJourAbsence(ETypeJourAbsence.CONGE_PAYE);
		j1.setDateDuJour(LocalDate.of(2020, Month.JANUARY, 1));
		j1.setCommentaire("Test");
		this.jourAbsenceRepo.save(j1);

		JourAbsence j2 = new JourAbsence();
		j2.setTypeJourAbsence(ETypeJourAbsence.CONGE_PAYE);
		j2.setDateDuJour(LocalDate.of(2020, Month.JANUARY, 2));
		j2.setCommentaire("Test");
		this.jourAbsenceRepo.save(j2);

		List<JourAbsence> listeTest = new ArrayList();
		listeTest.add(j1);
		listeTest.add(j2);

		Absence absence1 = new Absence();
		absence1.setDatePremierJourAbsence(LocalDate.of(2020, Month.JANUARY, 1));
		absence1.setDateDernierJourAbsence(LocalDate.of(2020, Month.JANUARY, 2));
		//absence1.setListeJourAbsence(listeTest);
		absence1.setCommentaireAbsence("Test absence");
		absence1.setStatutDemandeAbsence(EStatutDemandeAbsence.EN_ATTENTE_VALIDATION);
		absence1.setCollegue(col5);
		this.absenceRepo.save(absence1);

		List<Absence> listeAbsence = new ArrayList();
		listeAbsence.add(absence1);
		col5.setListeAbsencesDuCollegue(listeAbsence);
		this.collegueRepo.save(col5);
	}

}
