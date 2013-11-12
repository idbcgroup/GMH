package org.fourgeeks.gha.domain.msg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractCodeEntity;
import org.fourgeeks.gha.domain.enu.LanguageEnum;

/**
 * @author alacret
 * 
 */
@Entity
@Table(schema = "msg", uniqueConstraints = @UniqueConstraint(columnNames = {
		"language", "code" }))
@NamedQueries(value = { @NamedQuery(name = "UiString.getByLanguage", query = "SELECT e from UiString e where e.language = :language order by e.code") })
public class UiString extends AbstractCodeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private LanguageEnum language;

	private String text;

	/**
	 */
	public UiString() {
		super();
	}

	/**
	 * @param language
	 * @param code
	 * @param text
	 */
	public UiString(LanguageEnum language, String code, String text) {
		super();
		this.language = language;
		this.code = code;
		this.text = text;
	}

	@Override
	public String toString() {
		return this.getText();
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return this.text;
	}
}
