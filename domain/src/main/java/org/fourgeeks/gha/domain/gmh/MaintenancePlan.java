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
		@NamedQuery(name = "MaintenancePlan.getAll", query = "SELECT e from MaintenancePlan e order by e.id"),
		@NamedQuery(name = "MaintenancePlan.findByEiaType", query = "SELECT mnt from EiaTypeMaintenancePlan e JOIN e.maintenancePlan mnt WHERE e.eiaType = :eiaType order by e.id") })
public class MaintenancePlan extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String description;

	/**
	 * 
	 */
	public MaintenancePlan() {
		// TODO Auto-generated constructor stub
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
