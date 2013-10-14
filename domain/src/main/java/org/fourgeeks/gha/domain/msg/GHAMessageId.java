package org.fourgeeks.gha.domain.msg;

import java.io.Serializable;

import org.fourgeeks.gha.domain.enu.LanguageEnum;

/**
 * @author emiliot
 * 
 */
public class GHAMessageId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String code;
	/**
	 * 
	 */
	private LanguageEnum language;

	/**
	 * 
	 */
	public GHAMessageId() {
	}

	/**
	 * @param code
	 * @param language
	 */
	public GHAMessageId(String code, LanguageEnum language) {
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
	 * @return the language
	 */
	public LanguageEnum getLanguage() {
		return language;
	}

	@Override
	public boolean equals(Object o) {
		return ((o instanceof GHAMessageId)
				&& code.equals(((GHAMessageId) o).getCode()) && language
					.equals(((GHAMessageId) o).getLanguage()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return code.hashCode() + language.hashCode();
	}
}