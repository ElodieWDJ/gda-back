package dev.domain;

public enum eRole {
	ROLE_UTILISATEUR(1), ROLE_ADMINISTRATEUR(2), ROLE_MANAGER(3);
	
	private Integer value;
	
	private eRole(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}
}
