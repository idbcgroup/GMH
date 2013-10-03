	/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 * 
 */

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "MaintenanceProtocol.findByMaintenancePlan", 
				query = "SELECT prot from MaintenancePlanMaintenanceProtocol e JOIN e.maintenanceProtocol prot WHERE e.maintenancePlan = :maintenancePlan ORDER BY e.id"),
		@NamedQuery(name = "MaintenanceProtocol.getAll",
				query = "SELECT e from MaintenanceProtocol e ORDER BY e.id")
})
public class MaintenanceProtocol extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String name;
	private String description;

	/**
	 * 
	 */
	public MaintenanceProtocol() {
		// TODO Auto-generated constructor stub
	}

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
