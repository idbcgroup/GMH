package org.fourgeeks.gha.domain.gmh;

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
		@NamedQuery(name = "Resource.getAll", query = "SELECT e from Resource e order by e.name"),
		@NamedQuery(name = "Resource.findByProtocolActivity", query = "SELECT r FROM  ProtocolActivityResource e JOIN e.resource r WHERE e.protocolActivity = :protocolActivity")})
public class Resource extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;

	/**
	 * 
	 */
	public Resource() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 */
	public Resource(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
