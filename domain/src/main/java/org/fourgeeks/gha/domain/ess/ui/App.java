package org.fourgeeks.gha.domain.ess.ui;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

/**
 * @author alacret
 * 
 */
@Entity
@Table(schema = "auth")
public class App extends AbstractCodeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String token;

	@ManyToOne
	@JoinColumn(name = "moduleFk", nullable = false)
	private Module module;

	/**
	 * 
	 */
	public App() {
	}

	/**
	 * @param module
	 * @param code
	 * @param token
	 */
	public App(Module module, String code, String token) {
		this.module = module;
		this.token = token;
		setCode(code);
	}

	/**
	 * @param module
	 * @param name
	 * @param code
	 * @param token
	 */
	public App(Module module, String name, String token, String code) {
		this.module = module;
		this.name = name;
		this.token = token;
		setCode(code);
	}

	/**
	 * @return the module
	 */
	public Module getModule() {
		return module;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

}