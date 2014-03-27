package org.fourgeeks.gha.domain.ess.ui;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

/**
 * @author alacret
 * 
 */
@Entity
@Table(schema = "ui")
public class App extends AbstractCodeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(nullable = false)
	private String name;

	@NotNull
	@Column(nullable = false)
	private String token;

	@ManyToOne
	@JoinColumn(name = "moduleFk", nullable = false)
	private Module module;

	@ManyToOne
	@JoinColumn(name = "menuFk", nullable = false)
	private Menu menu;

	/**
	 * 
	 */
	public App() {
	}

	/**
	 * @param module
	 * @param name
	 * @param code
	 * @param token
	 * @param menu
	 */
	public App(final Module module, final String name, final String code,
			final String token, final Menu menu) {
		this.module = module;
		this.name = name;
		this.token = token;
		this.menu = menu;
		setCode(code);
	}

	/**
	 * @return the from wich it belongs
	 */
	public Menu getMenu() {
		return menu;
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