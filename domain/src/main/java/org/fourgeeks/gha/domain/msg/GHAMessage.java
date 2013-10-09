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
@NamedQueries(value = { @NamedQuery(name = "GHAMessage.find", query = "SELECT e FROM GHAMessage e WHERE e.code = :code AND e.language = :language") })
public class GHAMessage extends AbstractCodeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private LanguageEnum language;

	private String text;

	/**
	 * @param language
	 * @param code
	 * @param text
	 */
	public GHAMessage(LanguageEnum language, String code, String text) {
		super();
		this.language = language;
		this.code = code;
		this.text = text;
	}

	/**
	 */
	public GHAMessage() {
		super();
	}

	/**
	 * @param language
	 * @param code
	 * @param text
	 */
	public GHAMessage(String code) {
		super();
		this.code = code;
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
