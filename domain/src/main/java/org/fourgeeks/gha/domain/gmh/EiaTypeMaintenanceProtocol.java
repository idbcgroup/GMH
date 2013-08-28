/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 * 
 */

@Entity
@NamedQueries(value = {
		@NamedQuery(name = "EiaTypeMaintenanceProtocol.findByEiaTypeMaintenancePlan", 
				query = "SELECT e from EiaTypeMaintenanceProtocol e WHERE e.eiaTypeMaintenancePlan = :eiaTypeMaintenancePlan ORDER BY e.id"),
		@NamedQuery(name = "EiaTypeMaintenanceProtocol.getAll",
				query = "SELECT e from EiaTypeMaintenanceProtocol e ORDER BY e.id")
})
public class EiaTypeMaintenanceProtocol extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "eiaTypeMaintenancePlanFk")
	private EiaTypeMaintenancePlan eiaTypeMaintenancePlan;

	private String description;

	/**
	 * 
	 */
	public EiaTypeMaintenanceProtocol() {
		// TODO Auto-generated constructor stub
	}

	public EiaTypeMaintenancePlan getEiaTypeMaintenancePlan() {
		return eiaTypeMaintenancePlan;
	}

	public String getDescription() {
		return description;
	}

	public void setEiaTypeMaintenancePlan(
			EiaTypeMaintenancePlan eiaTypeMaintenancePlan) {
		this.eiaTypeMaintenancePlan = eiaTypeMaintenancePlan;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
