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
 * @author emiliot
 * 
 */

@Deprecated
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {
		"maintenancePlanFk", "maintenanceProtocolFk", "ordinal" }))
@NamedQueries(value = {
		@NamedQuery(name = "MaintenancePlanMaintenanceProtocol.findByMaintenancePlan", query = "SELECT e from MaintenancePlanMaintenanceProtocol e WHERE e.maintenancePlan = :maintenancePlan ORDER BY e.id"),
		@NamedQuery(name = "MaintenancePlanMaintenanceProtocol.findByMaintenanceProtocol", query = "SELECT e from MaintenancePlanMaintenanceProtocol e WHERE e.maintenanceProtocol = :maintenanceProtocol ORDER BY e.id") })
public class MaintenancePlanMaintenanceProtocol extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "maintenancePlanFk")
	private MaintenancePlan maintenancePlan;

	@ManyToOne
	@JoinColumn(name = "maintenanceProtocolFk")
	private MaintenanceProtocol maintenanceProtocol;

	private int ordinal;

	/**
	 * 
	 */
	public MaintenancePlanMaintenanceProtocol() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param maintenancePlan
	 * @param maintenanceProtocol
	 * @param ordinal
	 */
	public MaintenancePlanMaintenanceProtocol(MaintenancePlan maintenancePlan,
			MaintenanceProtocol maintenanceProtocol, int ordinal) {
		this.maintenancePlan = maintenancePlan;
		this.maintenanceProtocol = maintenanceProtocol;
		this.ordinal = ordinal;
	}

	public MaintenancePlan getMaintenancePlan() {
		return maintenancePlan;
	}

	public void setMaintenancePlan(MaintenancePlan maintenancePlan) {
		this.maintenancePlan = maintenancePlan;
	}

	public MaintenanceProtocol getMaintenanceProtocol() {
		return maintenanceProtocol;
	}

	public void setMaintenanceProtocol(MaintenanceProtocol maintenanceProtocol) {
		this.maintenanceProtocol = maintenanceProtocol;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}

}
