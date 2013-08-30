/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 *
 */

@Entity
public class EiaMaintenancePlanification extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "eiaTypeMaintenancePlanFk")
	private EiaTypeMaintenancePlan maintenancePlan;
	
	@ManyToOne
	@JoinColumn(name = "eiaFk")
	private Eia eia;
	
	private Date scheduledDate;
	
	/**
	 * 
	 */
	public EiaMaintenancePlanification() {
		// TODO Auto-generated constructor stub
	}

	public EiaTypeMaintenancePlan getMaintenancePlan() {
		return maintenancePlan;
	}

	public Eia getEia() {
		return eia;
	}

	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setMaintenancePlan(EiaTypeMaintenancePlan maintenancePlan) {
		this.maintenancePlan = maintenancePlan;
	}

	public void setEia(Eia eia) {
		this.eia = eia;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
}
