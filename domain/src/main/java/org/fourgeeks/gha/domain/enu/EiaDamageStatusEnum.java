package org.fourgeeks.gha.domain.enu;

public enum EiaDamageStatusEnum {
	FAILURE("Equipo con Falla"), DAMAGE("Equipo Dañado");

	private String name;

	private EiaDamageStatusEnum(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
