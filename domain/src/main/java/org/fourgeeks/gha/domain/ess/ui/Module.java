package org.fourgeeks.gha.domain.ess.ui;

import javax.persistence.Entity;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

/**
 * @author alacret
 * 
 */
@Entity
public class Module extends AbstractCodeEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public Module() {
	}

	/**
	 * @param name
	 * @param code
	 */
	public Module(String name, String code) {
		this.name = name;
		setCode(code);
	}

	/**
	 * @return the name of the module
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	private String name;
}