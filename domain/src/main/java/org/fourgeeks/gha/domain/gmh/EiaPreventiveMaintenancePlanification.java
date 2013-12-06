package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.MaintenancePlanState;
import org.fourgeeks.gha.domain.enu.MaintenancePlanStatus;

/**
 * @author emiliot
 * 
 */
@Entity
public class EiaPreventiveMaintenancePlanification extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@NotNull(message = "eiatype-maintenance-plan-not-null")
	@JoinColumn(name = "eiaTypeMaintenancePlanFk", nullable = false)
	private EiaTypeMaintenancePlan plan;

	@OneToOne
	@JoinColumn(name = "eiaMaintenancePlanificationFk", nullable = false)
	private EiaMaintenancePlanification planification;

	/** status del plan de mantenimiento para el equipo */
	private MaintenancePlanStatus eiaPlanStatus;

	/** estado del plan de mantenimiento para el equipo (activo, inactivo) */
	private MaintenancePlanState eiaPlanState;

	/**
	 * 
	 */
	public EiaPreventiveMaintenancePlanification() {
		super();
	}

	/**
	 * @return the plan
	 */
	public EiaTypeMaintenancePlan getPlan() {
		return plan;
	}

	/**
	 * @param plan
	 *            the plan to set
	 */
	public void setPlan(EiaTypeMaintenancePlan plan) {
		this.plan = plan;
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

	public MaintenancePlanStatus getEiaPlanStatus() {
		return eiaPlanStatus;
	}

	public void setEiaPlanStatus(MaintenancePlanStatus planStatus) {
		this.eiaPlanStatus = planStatus;
	}

	public MaintenancePlanState getEiaPlanState() {
		return eiaPlanState;
	}

	public void setEiaPlanState(MaintenancePlanState eiaPlanState) {
		this.eiaPlanState = eiaPlanState;
	}

}
