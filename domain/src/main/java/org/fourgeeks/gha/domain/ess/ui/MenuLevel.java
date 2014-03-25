/**
 * 
 */
package org.fourgeeks.gha.domain.ess.ui;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

/**
 * @author alacret
 * 
 */
@Entity
@Table(schema = "ui")
public class MenuLevel extends AbstractCodeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "parentFk", nullable = true)
	private MenuLevel parentMenu;

	private String text;

	/**
	 * Creates a empty menu
	 */
	public MenuLevel() {
		super();
	}

	/**
	 * Creates a menu with the given text and parent
	 * 
	 * @param menu
	 * @param code
	 * @param text
	 */
	public MenuLevel(final String code, final String text, final MenuLevel menu) {
		this();
		this.code = code;
		this.text = text;
		this.parentMenu = menu;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(final String text) {
		this.text = text;
	}

}