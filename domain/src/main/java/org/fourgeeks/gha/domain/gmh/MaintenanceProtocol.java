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
		@NamedQuery(name = "MaintenanceProtocol.findByMaintenancePlan", query = "SELECT e from MaintenanceProtocol e WHERE e.maintenancePlan = :plan ORDER BY e.ordinal"),
		@NamedQuery(name = "MaintenanceProtocol.getLastOrdinal", query = "SELECT MAX(mp.ordinal) FROM MaintenanceProtocol mp WHERE mp.maintenancePlan = :plan"),
		@NamedQuery(name = "MaintenanceProtocol.findByMantenanceActivity", query = "SELECT mp FROM MaintenanceProtocol mp WHERE mp.maintenanceActivity = :maintenanceActivity ") })
public class MaintenanceProtocol extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "maintenancePlanFk", columnDefinition = "bigint REFERENCES maintenanceplan(id) ON UPDATE CASCADE ON DELETE CASCADE")
	private MaintenancePlan maintenancePlan;

	@ManyToOne
	@JoinColumn(name = "maintenanceActivityFk", columnDefinition = "bigint REFERENCES maintenanceactivity(id) ON UPDATE CASCADE ON DELETE CASCADE")
	private MaintenanceActivity maintenanceActivity;

	private int ordinal;

	/** */
	public MaintenanceProtocol() {
	}

	/**
	 * @param maintenancePlan
	 * @param maintenanceActivity
	 * @param ordinal
	 */
	public MaintenanceProtocol(MaintenancePlan maintenancePlan,
			MaintenanceActivity maintenanceActivity, int ordinal) {
		this.maintenancePlan = maintenancePlan;
		this.maintenanceActivity = maintenanceActivity;
		this.ordinal = ordinal;
	}

	/**
	 * @return the maintenanceActivity
	 */
	public MaintenanceActivity getMaintenanceActivity() {
		return maintenanceActivity;
	}

	/**
	 * @return the maintenancePlan
	 */
	public MaintenancePlan getMaintenancePlan() {
		return maintenancePlan;
	}

	/**
	 * @return the ordinal
	 */
	public int getOrdinal() {
		return ordinal;
	}

	/**
	 * @param maintenanceActivity
	 *            the maintenanceActivity to set
	 */
	public void setMaintenanceActivity(MaintenanceActivity maintenanceActivity) {
		this.maintenanceActivity = maintenanceActivity;
	}

	/**
	 * @param maintenancePlan
	 *            the maintenancePlan to set
	 */
	public void setMaintenancePlan(MaintenancePlan maintenancePlan) {
		this.maintenancePlan = maintenancePlan;
	}

	/**
	 * @param ordinal
	 *            the ordinal to set
	 */
	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}

}
