package dev.domain.entite;

public enum ERole {
	ROLE_UTILISATEUR(1), ROLE_ADMINISTRATEUR(2), ROLE_MANAGER(3);
	
	private Integer value;
	
	private ERole(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}
