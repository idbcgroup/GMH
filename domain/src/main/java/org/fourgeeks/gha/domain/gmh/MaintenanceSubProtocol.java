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
		@NamedQuery(name = "MaintenanceSubProtocol.findByProtocolActivity", query = "SELECT e FROM  MaintenanceSubProtocol e WHERE e.parentMaintenanceActivity = :parentMaintenanceActivity ORDER BY e.ordinal")})
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
	private MaintenanceActivity parentMaintenanceActivity;
	
	/**
	 * Number of order for activity
	 */
	private int ordinal;

	/**
	 * 
	 */
	public MaintenanceSubProtocol() {
	}

	public MaintenanceActivity getMaintenanceActivity() {
		return maintenanceActivity;
	}

	public MaintenanceActivity getParentProtocolActivity() {
		return parentMaintenanceActivity;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public void setMaintenanceActivity(MaintenanceActivity maintenanceActivity) {
		this.maintenanceActivity = maintenanceActivity;
	}

	public void setParentProtocolActivity(MaintenanceActivity parentMaintenanceActivity) {
		this.parentMaintenanceActivity = parentMaintenanceActivity;
	}

	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}
	
	
}