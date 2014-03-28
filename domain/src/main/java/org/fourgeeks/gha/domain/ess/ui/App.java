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
	private MenuLevel menu;

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
			final String token, final MenuLevel menu) {
		this.module = module;
		this.name = name;
		this.token = token;
		this.menu = menu;
		setCode(code);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		App other = (App) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	/**
	 * @return the from wich it belongs
	 */
	public MenuLevel getMenuLevel() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		return result;
	}

}