package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 * 
 */
@Entity
public class EiaCorrectiveMaintenancePlanification extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "eiaMaintenancePlanificationFk")
	private EiaMaintenancePlanification planification;

	// TODO agregar una clave con el reporte de daño

	/**
	 * 
	 */
	public EiaCorrectiveMaintenancePlanification() {
		super();
	}

	/**
	 * @return the planification
	 */
	public EiaMaintenancePlanification getPlanification() {
		return planification;
	}

	/**
	 * @param planification
	 *            the planification to set
	 */
	public void setPlanification(EiaMaintenancePlanification planification) {
		this.planification = planification;
	}

}
