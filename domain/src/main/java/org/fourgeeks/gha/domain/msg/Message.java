/**
 * 
 */
package org.fourgeeks.gha.domain.msg;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.LanguageEnum;

/**
 * @author emiliot
 *
 */
@Entity
@Table(name = "Message", schema = "GHAMsg")
public class Message extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String code;
	private LanguageEnum language;
	private String message;
	/**
	 * 
	 */
	public Message() {
		// TODO Auto-generated constructor stub
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public LanguageEnum getLanguage() {
		return language;
	}
	public void setLanguage(LanguageEnum language) {
		this.language = language;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
