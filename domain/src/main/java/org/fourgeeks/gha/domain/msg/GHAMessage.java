package org.fourgeeks.gha.domain.msg;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.enu.LanguageEnum;

/**
 * @author alacret
 * 
 */
@Entity
@IdClass(GHAMessageId.class)
@Table(schema = "msg")
@NamedQueries(value = { @NamedQuery(name = "GHAMessage.getAllByCodes", query = "SELECT e from GHAMessage e WHERE e.code IN (:codes) AND e.language = :language ORDER BY e.code") })
public class GHAMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull(message = "code-not-null")
	private String code;
	@Id
	@NotNull(message = "language-not-null")
	private LanguageEnum language;

	private String text;

	/**
	 */
	public GHAMessage() {
		super();
	}

	/**
	 * @param code
	 * @param language
	 */
	public GHAMessage(String code, LanguageEnum language) {
		this.code = code;
		this.language = language;
	}

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
	 * @param code
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

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
}
