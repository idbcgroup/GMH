package org.fourgeeks.gha.domain.ess.ui;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

/**
 * @author alacret
 * 
 */
@Entity
@Table(schema = "auth")
public class View extends AbstractCodeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name, description;

	/**
	 * 
	 */
	public View() {
	}

	/**
	 * @param app
	 * @param name
	 * @param code
	 * @param description
	 */
	public View(String code, String name, String description) {
		this.name = name;
		setCode(code);
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}