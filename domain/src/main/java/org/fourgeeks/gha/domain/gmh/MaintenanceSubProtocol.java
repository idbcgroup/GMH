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
 * @author alacret
 * 
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {
		"maintenanceActivityFk", "parentMaintenanceActivityFk" }))
@NamedQueries(value = {
		@NamedQuery(name = "MaintenanceSubProtocol.getAll", query = "SELECT e from MaintenanceSubProtocol e order by e.id"),
		@NamedQuery(name = "MaintenanceSubProtocol.findByProtocolActivity", query = "SELECT e FROM  MaintenanceSubProtocol e WHERE e.parentProtocolActivity = :parentMaintenanceActivity ORDER BY e.ordinal") })
public class MaintenanceSubProtocol extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "maintenanceActivityFk")
	private MaintenanceActivity maintenanceActivity;

	@ManyToOne
	@JoinColumn(name = "parentMaintenanceActivityFk")
	private MaintenanceActivity parentProtocolActivity;

	/**
	 * Number of order for activity
	 */
	private int ordinal;

	/**
	 * 
	 */
	public MaintenanceSubProtocol() {
	}

	/**
	 * @param maintenanceActivity
	 * @param parentProtocolActivity
	 * @param ordinal
	 */
	public MaintenanceSubProtocol(MaintenanceActivity parentProtocolActivity,
			MaintenanceActivity maintenanceActivity, int ordinal) {
		this.maintenanceActivity = maintenanceActivity;
		this.parentProtocolActivity = parentProtocolActivity;
		this.ordinal = ordinal;
	}

	/**
	 * @return the maintenance actvity
	 */
	public MaintenanceActivity getMaintenanceActivity() {
		return maintenanceActivity;
	}

	/**
	 * @return the parent protocol maintenance activity
	 */
	public MaintenanceActivity getParentProtocolActivity() {
		return parentProtocolActivity;
	}

	/**
	 * @return the ordinal
	 */
	public int getOrdinal() {
		return ordinal;
	}

	/**
	 * @param maintenanceActivity
	 *            the maintenance activity
	 */
	public void setMaintenanceActivity(MaintenanceActivity maintenanceActivity) {
		this.maintenanceActivity = maintenanceActivity;
	}

	/**
	 * @param parentMaintenanceActivity
	 *            the parent protocol maintenance activity
	 */
	public void setParentProtocolActivity(
			MaintenanceActivity parentMaintenanceActivity) {
		this.parentProtocolActivity = parentMaintenanceActivity;
	}

	/**
	 * @param ordinal
	 *            the ordinal
	 */
	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}

}