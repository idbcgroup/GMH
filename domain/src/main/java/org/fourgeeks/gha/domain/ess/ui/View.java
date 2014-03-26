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
public class View extends AbstractCodeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;

	/**
	 * 
	 */
	public View() {
	}

	/**
	 * @param name
	 * @param code
	 */
	public View(final String code, final String name) {
		this.name = name;
		setCode(code);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}