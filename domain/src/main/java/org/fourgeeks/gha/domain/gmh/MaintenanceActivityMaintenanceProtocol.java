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

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "protocolFk",
		"activityFk", "ordinal" }))
@NamedQueries(value = {
		@NamedQuery(name = "MaintenanceActivityMaintenanceProtocol.findByMaintenanceActivity", query = "SELECT e from MaintenanceActivityMaintenanceProtocol e WHERE e.activity = :maintenanceActivity ORDER BY e.id"),
		@NamedQuery(name = "MaintenanceActivityMaintenanceProtocol.findByMaintenanceProtocol", query = "SELECT e from MaintenanceActivityMaintenanceProtocol e WHERE e.protocol = :maintenanceProtocol ORDER BY e.id") })
public class MaintenanceActivityMaintenanceProtocol extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "protocolFk")
	private MaintenanceProtocol protocol;

	@ManyToOne
	@JoinColumn(name = "activityFk")
	private MaintenanceActivity activity;

	private int ordinal;

	/**
	 * 
	 */
	public MaintenanceActivityMaintenanceProtocol() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param protocol
	 * @param activity
	 * @param ordinal
	 */
	public MaintenanceActivityMaintenanceProtocol(MaintenanceProtocol protocol,
			MaintenanceActivity activity, int ordinal) {
		this.protocol = protocol;
		this.activity = activity;
		this.ordinal = ordinal;
	}

	public MaintenanceProtocol getProtocol() {
		return protocol;
	}

	public MaintenanceActivity getActivity() {
		return activity;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public void setProtocol(MaintenanceProtocol protocol) {
		this.protocol = protocol;
	}

	public void setActivity(MaintenanceActivity activity) {
		this.activity = activity;
	}

	public void setOrdinal(int order) {
		this.ordinal = order;
	}

}
