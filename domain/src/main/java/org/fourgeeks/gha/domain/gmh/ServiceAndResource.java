package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * Entity to represent resources and services
 * 
 * @author emiliot, naramirez
 */
@Entity
@NamedQueries(value = {
		@NamedQuery(name = "ServiceAndResource.getAll", query = "SELECT e from ServiceAndResource e order by e.name"),
		@NamedQuery(name = "ServiceAndResource.findByActivity", query = "SELECT r FROM  RequiredResources e JOIN e.resource r WHERE e.activity = :activity") })
public class ServiceAndResource extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	/**
	 * 
	 */
	public ServiceAndResource() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 */
	public ServiceAndResource(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
