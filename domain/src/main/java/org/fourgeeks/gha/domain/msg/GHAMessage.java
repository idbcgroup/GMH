package org.fourgeeks.gha.domain.msg;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.enu.LanguageEnum;

/**
 * @author alacret, jfuentes
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

	@ManyToOne
	@JoinColumn(name = "typeFk", nullable = true, columnDefinition = "character varying(255) DEFAULT 'SAY'::character varying")
	private GHAMessageType type;

	private String text;

	private String indications;

	private int time;

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
	public GHAMessage(LanguageEnum language, String code, String text) {
		super();
		this.language = language;
		this.code = code;
		this.text = text;
	}

	/**
	 * @param language
	 * @param code
	 * @param text
	 * @param indications
	 * @param type
	 * @param time
	 */
	public GHAMessage(LanguageEnum language, String code, String text, String indications,
			GHAMessageType type, int time) {
		super();
		this.code = code;
		this.language = language;
		this.type = type;
		this.text = text;
		this.indications = indications;
		this.time = time;
	}

	/**
	 * @param code
	 */
	public GHAMessage(String code) {
		super();
		this.code = code;
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the lang
	 */
	public LanguageEnum getLang() {
		return language;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * @return the type
	 */
	public GHAMessageType getType() {
		return type;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * @param type
	 *            the type of message
	 */
	public void setType(GHAMessageType type) {
		this.type = type;
	}

	/**
	 * @return the indications
	 */
	public String getIndications() {
		return indications;
	}

	/**
	 * @param indications
	 */
	public void setIndications(String indications) {
		this.indications = indications;
	}

	/**
	 * @return the time that the message will remain open.
	 */
	public int getTime() {
		return time;
	}

	/**
	 * @param time
	 */
	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return this.getText()+"."+this.getIndications();
	}
}
