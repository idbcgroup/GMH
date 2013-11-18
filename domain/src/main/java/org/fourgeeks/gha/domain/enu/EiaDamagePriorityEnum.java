package org.fourgeeks.gha.domain.enu;

public enum EiaDamagePriorityEnum {
	NORMAL("Normal"), HIGH("Alta");

	private String name;

	private EiaDamagePriorityEnum(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
