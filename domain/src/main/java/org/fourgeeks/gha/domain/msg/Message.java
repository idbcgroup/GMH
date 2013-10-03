package org.fourgeeks.gha.domain.msg;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Message extends AbstractCodeEntity {

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
	public Message(LanguageEnum language, String code, String text) {
		super();
		this.language = language;
		this.code = code;
		this.text = text;
	}

	/**
	 */
	public Message() {
		super();
	}

	/**
	 * @param language
	 * @param code
	 * @param text
	 */
	public Message(String code) {
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
