/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.MaintenancePlanificationState;
import org.fourgeeks.gha.domain.gar.Job;
import org.fourgeeks.gha.domain.glm.Bsp;

/**
 * @author emiliot
 * 
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "eiaFk",
		"eiaTypeMaintenancePlanFk" }))
@NamedQueries(value = {
		@NamedQuery(name = "EiaMaintenancePlanification.findByEiaType", query = "SELECT emp FROM EiaMaintenancePlanification emp JOIN emp.plan plan WHERE plan.eiaType = :eiaType ORDER BY emp.id"),
		@NamedQuery(name = "EiaMaintenancePlanification.getAll", query = "SELECT emp FROM EiaMaintenancePlanification emp ORDER BY emp.id") })
public class EiaMaintenancePlanification extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	/**
	 * El equipo al que se la aplicara el plan de mantenimiento
	 */
	@ManyToOne
	@JoinColumn(name = "eiaFk")
	private Eia eia;

	/**
	 * plan de mantenimiento que se asociará con el equipo para crear la
	 * planificación de mantenimiento.
	 */
	@ManyToOne
	@NotNull(message = "eiatype-maintenance-plan-not-null")
	@JoinColumn(name = "eiaTypeMaintenancePlanFk", nullable = false)
	private EiaTypeMaintenancePlan plan;

	/**
	 * Es el proveedor de servicio responsable de aplicar el plan de
	 * mantenimiento al equipo.
	 */
	@ManyToOne
	@JoinColumn(name = "maintenanceProviderFk")
	private Bsp maintenanceProvider;

	/**
	 * Es el responsable de velar por la ejecución del plan mantenimiento al
	 * equipo
	 */
	@ManyToOne
	@JoinColumn(name = "jobResponsableFk")
	private Job jobResponsable;

	/**
	 * Estado de la planificación de mantenimiento: permite activar o
	 * desactiviar el plan para este equipo
	 */
	private MaintenancePlanificationState planificationState;

	/** fecha en que ha de comenzar el mantenimiento. */
	private Date beginningDate;

	/** */
	public EiaMaintenancePlanification() {
	}

	/**
	 * @param eia
	 * @param plan
	 */
	public EiaMaintenancePlanification(Eia eia, EiaTypeMaintenancePlan plan) {
		this.eia = eia;
		this.plan = plan;
	}

	/**
	 * @return the eia
	 */
	public Eia getEia() {
		return eia;
	}

	/**
	 * @return the role
	 */
	public Job getJobResponsable() {
		return jobResponsable;
	}

	/**
	 * @return the provider
	 */
	public Bsp getMaintenanceProvider() {
		return maintenanceProvider;
	}

	/**
	 * @return the plan
	 */
	public EiaTypeMaintenancePlan getPlan() {
		return plan;
	}

	/**
	 * @return the state of the planification
	 */
	public MaintenancePlanificationState getPlanificationState() {
		return planificationState;
	}

	/**
	 * @return the beginningDate
	 */
	public Date getBeginningDate() {
		return beginningDate;
	}

	/**
	 * @param eia
	 *            the eia to set
	 */
	public void setEia(Eia eia) {
		this.eia = eia;
	}

	/**
	 * @param jobResponsable
	 *            the role to set
	 */
	public void setJobResponsable(Job jobResponsable) {
		this.jobResponsable = jobResponsable;
	}

	/**
	 * @param maintenanceProvider
	 *            the provider to set
	 */
	public void setMaintenanceProvider(Bsp maintenanceProvider) {
		this.maintenanceProvider = maintenanceProvider;
	}

	/**
	 * @param plan
	 *            the plan to set
	 */
	public void setPlan(EiaTypeMaintenancePlan plan) {
		this.plan = plan;
	}

	/**
	 * @param planificationState
	 */
	public void setPlanificationState(
			MaintenancePlanificationState planificationState) {
		this.planificationState = planificationState;
	}

	/**
	 * @param beginningDate
	 *            the beginningDate to set
	 */
	public void setBeginningDate(Date beginningDate) {
		this.beginningDate = beginningDate;
	}
}
