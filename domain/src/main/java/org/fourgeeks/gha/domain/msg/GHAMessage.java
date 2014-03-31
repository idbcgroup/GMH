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
	@JoinColumn(name = "messageTypeFk", nullable = true, columnDefinition = "character varying(255) DEFAULT 'SAY'::character varying")
	private GHAMessageType messageType;

	private String messageText;

	private String messageIndications;

	private int timeShowing;

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
		this.messageText = text;
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
		this.messageType = type;
		this.messageText = text;
		this.messageIndications = indications;
		this.timeShowing = time;
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
	public String getMessageCode() {
		return code;
	}

	/**
	 * @return the indications
	 */
	public String getMessageIndications() {
		return messageIndications;
	}

	/**
	 * @return the lang
	 */
	public LanguageEnum getLanguage() {
		return language;
	}

	/**
	 * @return the text
	 */
	public String getMessageText() {
		return this.messageText;
	}

	/**
	 * @return the type
	 */
	public GHAMessageType getMessageType() {
		return messageType;
	}

	/**
	 * @return the time that the message will remain open.
	 */
	public int getTimeShowing() {
		return timeShowing;
	}

	/**
	 * @param indications
	 */
	public void setMessageIndications(String indications) {
		this.messageIndications = indications;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setMessageText(String text) {
		this.messageText = text;
	}

	/**
	 * @param type
	 *            the type of message
	 */
	public void setMessageType(GHAMessageType type) {
		this.messageType = type;
	}

	/**
	 * @param time
	 */
	public void setTimeShowing(int time) {
		this.timeShowing = time;
	}

	@Override
	public String toString() {
		return this.getMessageText()+"."+this.getMessageIndications();
	}
}
