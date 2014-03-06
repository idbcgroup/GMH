package org.fourgeeks.gha.domain.gmh;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * @author emiliot
 * 
 */
@Entity
@DiscriminatorValue("preventive")
@NamedQueries(value = { @NamedQuery(name = "EiaPreventiveMaintenance.findByEiaType", query = "SELECT epm FROM EiaPreventiveMaintenance epm JOIN epm.planification planif WHERE planif.eia.eiaType = :eiaType ORDER BY epm.id") })
public class EiaPreventiveMaintenance extends EiaMaintenance {
	private static final long serialVersionUID = 1L;

	/**
	 * La planificacion de mantenimiento asociada al mantenimiento preventivo
	 */
	@ManyToOne
	@JoinColumn(name = "maintenancePlanificationFk", nullable = false)
	private EiaMaintenancePlanification planification;

	// TODO lista de consumibles y partes cambiadas al equipo

	/**
	 * 
	 */
	public EiaPreventiveMaintenance() {
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
