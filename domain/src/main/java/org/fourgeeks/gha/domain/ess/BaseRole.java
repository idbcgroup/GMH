/**
 * 
 */
package org.fourgeeks.gha.domain.ess;

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
		@NamedQuery(name = "BaseRole.getAll", 
				query = "SELECT e from BaseRole e order by e.name"),
		@NamedQuery(name = "BaseRole.findByName",
				query = "SELECT e from BaseRole e where lower(e.name) like :name ")
})
public class BaseRole extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String name;

	/**
	 * 
	 */
	public BaseRole() {
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
	public void setName(String name) {
		this.name = name;
	}

}
