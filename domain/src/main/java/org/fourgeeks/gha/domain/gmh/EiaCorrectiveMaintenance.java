package org.fourgeeks.gha.domain.gmh;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.fourgeeks.gha.domain.enu.TimePeriodEnum;

/**
 * @author emiliot
 * 
 */
@Entity
@DiscriminatorValue("corrective")
public class EiaCorrectiveMaintenance extends EiaMaintenance {
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "eiaDamageReportFk")
	private EiaDamageReport damageReport;

	/**
	 * Sirve para describir el daño causado en el equipo y/o la causa de que el
	 * equipo presente la falla o el daño.
	 */
	private String description;

	/**
	 * Tiempo estimado sin disponer del equipo.
	 */
	private int estimatedMaintenance;

	/**
	 * periodo de tiempo sin disponer del equipo
	 */
	private TimePeriodEnum estimatedMaintenancePoT;

	/**
	 * 
	 */
	public EiaCorrectiveMaintenance() {
		super();
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
