package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 * Entity to represent resources and services
 */
@Entity
@NamedQueries(value = {
		@NamedQuery(name = "RaS.getAll", query = "SELECT e from RaS e order by e.name"),
		@NamedQuery(name = "RaS.findByProtocolActivity", query = "SELECT r FROM  ProtocolActivityRaS e JOIN e.ras r WHERE e.protocolActivity = :protocolActivity")})
public class RaS extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;

	/**
	 * 
	 */
	public RaS() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 */
	public RaS(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
