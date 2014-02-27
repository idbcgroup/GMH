package org.fourgeeks.gha.domain.ess.ui;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

/**
 * @author alacret
 * 
 */
@Entity
@Table(schema = "ui")
public class Module extends AbstractCodeEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	/**
	 * 
	 */
	public Module() {
	}

	/**
	 * @param name
	 * @param code
	 */
	public Module(String code, String name) {
		this.name = name;
		setCode(code);
	}

	/**
	 * @return the name of the module
	 */
	public String getName() {
		return name;
	}
}