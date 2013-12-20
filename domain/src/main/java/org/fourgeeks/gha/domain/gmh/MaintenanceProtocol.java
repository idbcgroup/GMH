/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 * 
 */

@Deprecated
@Entity
@NamedQueries(value = {
		@NamedQuery(name = "MaintenanceProtocol.findByMaintenancePlan", query = "SELECT prot from MaintenancePlanMaintenanceProtocol e JOIN e.maintenanceProtocol prot WHERE e.maintenancePlan = :maintenancePlan ORDER BY e.id"),
		@NamedQuery(name = "MaintenanceProtocol.getAll", query = "SELECT e from MaintenanceProtocol e ORDER BY e.id") })
public class MaintenanceProtocol extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Size(max = 100)
	@NotNull(message = "name-not-null")
	@Column(nullable = false)
	private String name;
	private String description;

	/**
	 * 
	 */
	public MaintenanceProtocol() {
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
