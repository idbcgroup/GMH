package org.fourgeeks.gha.domain.ess;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

/**
 * @author alacret
 * 
 */
@Entity
public class Screen extends AbstractCodeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String token;

	@ManyToOne
	private Module module;

	/**
	 * 
	 */
	public Screen() {
	}

	/**
	 * @param module
	 * @param name
	 * @param code
	 * @param token
	 */
	public Screen(Module module, String name, String code, String token) {
		this.module = module;
		this.name = name;
		this.token = token;
		setCode(code);
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

}