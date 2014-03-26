package org.fourgeeks.gha.domain.ess.auth;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.fourgeeks.gha.domain.AbstractCodeEntity;

/**
 * @author alacret
 * 
 */
@Entity
@Table(schema = "auth")
@NamedQueries(value = { @NamedQuery(name = "Function.getAll", query = "SELECT e from Function e order by e.name") })
public class Function extends AbstractCodeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	/**
	 * 
	 */
	public Function() {
	}

	/**
	 * @param code
	 * @param name
	 */
	public Function(final String code, final String name) {
		this.name = name;
		this.code = code;
	}

	/**
	 * @return the name of the function
	 */
	public String getName() {
		return name;
	}

}