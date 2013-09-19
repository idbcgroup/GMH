/**
 * 
 */
package org.fourgeeks.gha.domain.msg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractCodeEntity;
import org.fourgeeks.gha.domain.enu.LanguageEnum;

/**
 * @author emiliot
 * 
 */
@Entity
@Table(name = "Message", schema = "GHAMsg", uniqueConstraints = @UniqueConstraint(columnNames = {
		"code", "language" }))
public class Message extends AbstractCodeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private LanguageEnum language;
	@Column(nullable = false)
	private String message;

	/**
	 * 
	 */
	public Message() {
	}

	/**
	 * @param code
	 * 
	 */
	public Message(String code) {
		this.code = code;
	}

	/**
	 * @param code
	 * @param languageEnum
	 * @param message
	 * 
	 */
	public Message(String code, LanguageEnum languageEnum, String message) {
		this.code = code;
		this.language = languageEnum;
		this.message = message;
	}

	/**
	 * @return the languague of the message
	 */
	public LanguageEnum getLanguage() {
		return language;
	}

	/**
	 * @param language
	 */
	public void setLanguage(LanguageEnum language) {
		this.language = language;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
