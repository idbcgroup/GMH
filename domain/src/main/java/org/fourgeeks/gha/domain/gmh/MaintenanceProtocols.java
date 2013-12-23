/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author naramirez
 * 
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {
		"maintenancePlanFk", "maintenanceActivityFk", "ordinal" }))
@NamedQueries(value = {
		@NamedQuery(name = "MaintenanceProtocols.findByMaintenancePlan", query = "SELECT e from MaintenanceProtocols e WHERE e.maintenancePlan = :plan ORDER BY e.ordinal"),
		@NamedQuery(name = "MaintenanceProtocols.getLastOrdinal", query = "SELECT MAX(mp.ordinal) FROM MaintenanceProtocols mp WHERE mp.maintenancePlan = :plan") })
public class MaintenanceProtocols extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "maintenancePlanFk")
	private MaintenancePlan maintenancePlan;

	@ManyToOne
	@JoinColumn(name = "maintenanceActivityFk")
	private MaintenanceActivity maintenanceActivity;

	private int ordinal;

	/** */
	public MaintenanceProtocols() {
	}

	/**
	 * @param maintenancePlan
	 * @param maintenanceActivity
	 * @param ordinal
	 */
	public MaintenanceProtocols(MaintenancePlan maintenancePlan,
			MaintenanceActivity maintenanceActivity, int ordinal) {
		this.maintenancePlan = maintenancePlan;
		this.maintenanceActivity = maintenanceActivity;
		this.ordinal = ordinal;
	}

	/**
	 * @return the maintenancePlan
	 */
	public MaintenancePlan getMaintenancePlan() {
		return maintenancePlan;
	}

	/**
	 * @param maintenancePlan
	 *            the maintenancePlan to set
	 */
	public void setMaintenancePlan(MaintenancePlan maintenancePlan) {
		this.maintenancePlan = maintenancePlan;
	}

	/**
	 * @return the maintenanceActivity
	 */
	public MaintenanceActivity getMaintenanceActivity() {
		return maintenanceActivity;
	}

	/**
	 * @param maintenanceActivity
	 *            the maintenanceActivity to set
	 */
	public void setMaintenanceActivity(MaintenanceActivity maintenanceActivity) {
		this.maintenanceActivity = maintenanceActivity;
	}

	/**
	 * @return the ordinal
	 */
	public int getOrdinal() {
		return ordinal;
	}

	/**
	 * @param ordinal
	 *            the ordinal to set
	 */
	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}

}
