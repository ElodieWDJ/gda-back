package dev.controller.vm;

import dev.domain.Collegue;
import dev.domain.eRole;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Structure modèlisant un collègue servant à communiquer avec l'extérieur (WEB API).
 */
public class CollegueVM {

    private String email;
    private String nom;
    private String prenom;

    private List<String> roles = new ArrayList<>();


    public CollegueVM(Collegue col) {
        this.email = col.getEmail();
        this.nom = col.getNom();
        this.prenom = col.getPrenom();
        this.roles = col.getRoles().stream().map( roleCollegue -> String.valueOf(roleCollegue.getRole().getValue())).collect(Collectors.toList());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
