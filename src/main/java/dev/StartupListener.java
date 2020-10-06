package dev;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.domain.Collegue;
import dev.domain.eRole;
import dev.domain.RoleCollegue;
import dev.domain.Version;
import dev.repository.CollegueRepo;
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

	public StartupListener(@Value("${app.version}") String appVersion, VersionRepo versionRepo,
			PasswordEncoder passwordEncoder, CollegueRepo collegueRepo) {
		this.appVersion = appVersion;
		this.versionRepo = versionRepo;
		this.passwordEncoder = passwordEncoder;
		this.collegueRepo = collegueRepo;
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
		col1.setRoles(Arrays.asList(new RoleCollegue(col1, eRole.ROLE_ADMINISTRATEUR),
				new RoleCollegue(col1, eRole.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col1);

		Collegue col2 = new Collegue();
		col2.setNom("User");
		col2.setPrenom("DEV");
		col2.setEmail("user@dev.fr");
		col2.setMotDePasse(passwordEncoder.encode("superpass"));
		col2.setRoles(Arrays.asList(new RoleCollegue(col2, eRole.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col2);

		Collegue col3 = new Collegue();
		col3.setNom("Bob");
		col3.setPrenom("Eponge");
		col3.setEmail("bobeponge@superApp.com");
		col3.setMotDePasse(passwordEncoder.encode("superpass"));
		col3.setRoles(Arrays.asList(new RoleCollegue(col3, eRole.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col3);

		Collegue col4 = new Collegue();
		col4.setNom("Boss");
		col4.setPrenom("Hugo");
		col4.setEmail("hugoboss@superApp.com");
		col4.setMotDePasse(passwordEncoder.encode("superpass"));
		col4.setRoles(Arrays.asList(new RoleCollegue(col4, eRole.ROLE_UTILISATEUR),
				new RoleCollegue(col4, eRole.ROLE_MANAGER)));
		this.collegueRepo.save(col4);

		Collegue col5 = new Collegue();
		col5.setNom("Gaston");
		col5.setPrenom("Hugo");
		col5.setEmail("hugogaston@superApp.com");
		col5.setMotDePasse(passwordEncoder.encode("superpass"));
		col5.setRoles(Arrays.asList(new RoleCollegue(col5, eRole.ROLE_UTILISATEUR)));
		this.collegueRepo.save(col5);
	}

}
