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
		@NamedQuery(name = "RoleBase.getAll", 
				query = "SELECT e from RoleBase e order by e.name"),
		@NamedQuery(name = "RoleBase.findByName",
				query = "SELECT e from RoleBase e where lower(e.name) like :name order by e.id")
})
public class RoleBase extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(nullable = false)
	private String name;

	/**
	 * 
	 */
	public RoleBase() {
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
