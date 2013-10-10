package org.fourgeeks.gha.domain.msg;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.enu.LanguageEnum;

/**
 * @author alacret
 * 
 */
@Entity
@IdClass(GHAMessageId.class)
@Table(schema = "msg", uniqueConstraints = @UniqueConstraint(columnNames = {
		"language", "code" }))
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
