package dev;

import dev.domain.Employe;
import dev.domain.Role;
import dev.domain.RoleEmploye;
import dev.domain.Version;
import dev.repository.CollegueRepo;
import dev.repository.VersionRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Code de démarrage de l'application.
 * Insertion de jeux de données.
 */
@Component
public class StartupListener {

    private String appVersion;
    private VersionRepo versionRepo;
    private PasswordEncoder passwordEncoder;
    private CollegueRepo collegueRepo;

    public StartupListener(@Value("${app.version}") String appVersion, VersionRepo versionRepo, PasswordEncoder passwordEncoder, CollegueRepo collegueRepo) {
        this.appVersion = appVersion;
        this.versionRepo = versionRepo;
        this.passwordEncoder = passwordEncoder;
        this.collegueRepo = collegueRepo;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void onStart() {
        this.versionRepo.save(new Version(appVersion));

        // Création de deux utilisateurs

        Employe col1 = new Employe();
        col1.setNom("Admin");
        col1.setPrenom("DEV");
        col1.setEmail("admin@dev.fr");
        col1.setMotDePasse(passwordEncoder.encode("superpass"));
        col1.setRoles(Arrays.asList(new RoleEmploye(col1, Role.ROLE_ADMINISTRATEUR), new RoleEmploye(col1, Role.ROLE_UTILISATEUR)));
        this.collegueRepo.save(col1);

        Employe col2 = new Employe();
        col2.setNom("User");
        col2.setPrenom("DEV");
        col2.setEmail("user@dev.fr");
        col2.setMotDePasse(passwordEncoder.encode("superpass"));
        col2.setRoles(Arrays.asList(new RoleEmploye(col2, Role.ROLE_UTILISATEUR)));
        this.collegueRepo.save(col2);
    }

}
