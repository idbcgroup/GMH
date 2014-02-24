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
	private String name, description;
	private App app;

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
	public View(App app, String code, String name, String description) {
		this.name = name;
		setCode(code);
		this.description = description;
		this.app = app;
	}

	/**
	 * @return the app
	 */
	public App getApp() {
		return app;
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