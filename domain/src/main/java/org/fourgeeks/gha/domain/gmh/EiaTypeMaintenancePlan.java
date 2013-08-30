/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 *
 */

@Entity
public class EiaTypeMaintenancePlan extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "eiaTypeFk")
	private EiaType eiaType;
	
	@ManyToOne
	@JoinColumn(name = "maintenancePlanFk")
	private MaintenancePlan maintenancePlan;

	/**
	 * 
	 */
	public EiaTypeMaintenancePlan() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param eiaType
	 * @param maintenancePlan
	 */
	public EiaTypeMaintenancePlan(EiaType eiaType,
			MaintenancePlan maintenancePlan) {
		this.eiaType = eiaType;
		this.maintenancePlan = maintenancePlan;
	}

	public EiaType getEiaType() {
		return eiaType;
	}

	public MaintenancePlan getMaintenancePlan() {
		return maintenancePlan;
	}

	public void setEiaType(EiaType eiaType) {
		this.eiaType = eiaType;
	}

	public void setMaintenancePlan(MaintenancePlan maintenancePlan) {
		this.maintenancePlan = maintenancePlan;
	}
	
	
}
