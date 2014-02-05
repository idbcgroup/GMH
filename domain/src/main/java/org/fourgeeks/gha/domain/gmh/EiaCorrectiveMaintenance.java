package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.AbstractEntity;
import org.fourgeeks.gha.domain.enu.TimePeriodEnum;

/**
 * @author emiliot
 * 
 */
@Entity
public class EiaCorrectiveMaintenance extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "eiaMaintenancePlanificationFk")
	private EiaMaintenancePlanification planification;

	@ManyToOne
	@JoinColumn(name = "eiaDamageReportFk")
	private EiaDamageReport damageReport;

	private String description;
	/**
	 * Represents the time units estimated that the eia will be in this
	 * maintenance
	 */
	private int estimatedMaintenance;
	/**
	 * Represents the time period estimated that the eia will be in this
	 * maintenance
	 */
	private TimePeriodEnum estimatedMaintenancePoT;

	/**
	 * 
	 */
	public EiaCorrectiveMaintenance() {
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

	/**
	 * @return the damageReport
	 */
	public EiaDamageReport getDamageReport() {
		return damageReport;
	}

	/**
	 * @param damageReport
	 *            the damageReport to set
	 */
	public void setDamageReport(EiaDamageReport damageReport) {
		this.damageReport = damageReport;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the estimatedMaintenance
	 */
	public int getEstimatedMaintenance() {
		return estimatedMaintenance;
	}

	/**
	 * @param estimatedMaintenance
	 *            the estimatedMaintenance to set
	 */
	public void setEstimatedMaintenance(int estimatedMaintenance) {
		this.estimatedMaintenance = estimatedMaintenance;
	}

	/**
	 * @return the estimatedMaintenancePoT
	 */
	public TimePeriodEnum getEstimatedMaintenancePoT() {
		return estimatedMaintenancePoT;
	}

	/**
	 * @param estimatedMaintenancePoT
	 *            the estimatedMaintenancePoT to set
	 */
	public void setEstimatedMaintenancePoT(
			TimePeriodEnum estimatedMaintenancePoT) {
		this.estimatedMaintenancePoT = estimatedMaintenancePoT;
	}

}
