package org.fourgeeks.gha.domain.ess;

import javax.persistence.Entity;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

/**
 * @author alacret
 * 
 */
@Entity
public class Module extends AbstractCodeEntity {
	public Module() {
	}

	public Module(String name, String code) {
		this.name = name;
		setCode(code);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
}