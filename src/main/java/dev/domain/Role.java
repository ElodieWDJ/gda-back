package dev.domain;

public enum Role {
	ROLE_UTILISATEUR(1), ROLE_ADMINISTRATEUR(2), ROLE_MANAGER(3);
	
	private Integer value;
	
	private Role(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

}
