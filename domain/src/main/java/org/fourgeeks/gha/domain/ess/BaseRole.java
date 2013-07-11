/**
 * 
 */
package org.fourgeeks.gha.domain.ess;

import javax.persistence.Entity;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author alacret
 * 
 */

@Entity
public class BaseRole extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	/**
	 * 
	 */
	public BaseRole() {
	}

	/**
	 * @return te name of the role
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
