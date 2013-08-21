/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 * 
 */

@Entity
@NamedQueries(value = { })
public class MaintenanceProtocol extends AbstractEntity {

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
	public MaintenanceProtocol() {
		// TODO Auto-generated constructor stub
	}

}
