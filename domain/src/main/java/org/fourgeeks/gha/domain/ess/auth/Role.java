/**
 * 
 */
package org.fourgeeks.gha.domain.ess.auth;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author alacret
 * 
 */

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "Role.getAll", query = "SELECT e from Role e order by e.name"),
		@NamedQuery(name = "Role.findByName", query = "SELECT e from Role e where lower(e.name) like :name order by e.id") })
public class Role extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String name;

	/**
	 * 
	 */
	public Role() {
	}

	/**
	 * Create a Role given the ID
	 * 
	 * @param id
	 */
	public Role(final long id) {
		setId(id);
	}

	/**
	 * Create a Role gicen the name
	 */
	public Role(final String name) {
		this.name = name;
	}

	/**
	 * @return the name of the role
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 *            The name of the Role
	 */
	public void setName(final String name) {
		this.name = name;
	}

}
