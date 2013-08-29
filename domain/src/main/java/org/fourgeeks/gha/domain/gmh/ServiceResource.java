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
		@NamedQuery(name = "ServiceResource.getAll", query = "SELECT e from ServiceResource e order by e.name"),
		@NamedQuery(name = "ServiceResource.findByMaintenanceActivity", query = "SELECT r FROM  MaintenanceActivityServiceResource e JOIN e.serviceResource r WHERE e.maintenanceActivity = :maintenanceActivity")})
public class ServiceResource extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;

	/**
	 * 
	 */
	public ServiceResource() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param name
	 */
	public ServiceResource(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
