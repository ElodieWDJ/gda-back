package dev.domain;

import javax.persistence.*;

@Entity
public class RoleEmploye {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;

    @Enumerated(EnumType.STRING)
    private Role role;

    public RoleEmploye() {
    }

    public RoleEmploye(Employe employe, Role role) {
        this.employe = employe;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe collegue) {
        this.employe = collegue;
    }
}
