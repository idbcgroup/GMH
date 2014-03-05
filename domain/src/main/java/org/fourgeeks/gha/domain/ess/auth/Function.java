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
	private String description;

	/**
	 * 
	 */
	public Function() {
	}

	/**
	 * @param code
	 * @param name
	 * @param description
	 */
	public Function(String code, String name, String description) {
		this.name = name;
		this.code = code;
		this.description = description;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the name of the function
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

}