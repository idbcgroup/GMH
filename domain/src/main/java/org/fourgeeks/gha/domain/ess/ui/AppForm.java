package org.fourgeeks.gha.domain.ess.ui;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

/**
 * @author alacret
 * 
 */
@Entity
public class AppForm extends AbstractCodeEntity {

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
	public AppForm() {
	}

	/**
	 * @param module
	 * @param name
	 * @param code
	 * @param token
	 */
	public AppForm(Module module, String name, String token, String code) {
		this.module = module;
		this.name = name;
		this.token = token;
		setCode(code);
	}

	/**
	 * @return the name
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

	/**
	 * @return the module
	 */
	public Module getModule() {
		return module;
	}

}