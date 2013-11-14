package org.fourgeeks.gha.domain.ess.ui;

import javax.persistence.Entity;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

/**
 * @author alacret
 * 
 */
@Entity
public class View extends AbstractCodeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String description;

	/**
	 * @param code
	 * 
	 */
	public View(String code) {
		setCode(code);
	}

	/**
	 * 
	 */
	public View() {
	}

	/**
	 * @param name
	 * @param code
	 * @param description
	 */
	public View(String code, String name, String description) {
		this.name = name;
		setCode(code);
		setDescription(description);
	}

	/**
	 * @return the name
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

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}