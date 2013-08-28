/**
 * 
 */
package org.fourgeeks.gha.domain.gmh;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.fourgeeks.gha.domain.AbstractEntity;

/**
 * @author emiliot
 *
 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "protocolFk", "activityFk", "ordinal" }))
public class ProtocolActivityMaintenanceProtocol extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "protocolFk")
	private MaintenanceProtocol protocol;
	
	@ManyToOne
	@JoinColumn(name = "activityFk")
	private ProtocolActivity activity;
	
	private int ordinal;

	/**
	 * 
	 */
	public ProtocolActivityMaintenanceProtocol() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param protocol
	 * @param activity
	 * @param ordinal
	 */
	public ProtocolActivityMaintenanceProtocol(
			MaintenanceProtocol protocol, ProtocolActivity activity,
			int ordinal) {
		this.protocol = protocol;
		this.activity = activity;
		this.ordinal = ordinal;
	}

	public MaintenanceProtocol getProtocol() {
		return protocol;
	}

	public ProtocolActivity getActivity() {
		return activity;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public void setProtocol(MaintenanceProtocol protocol) {
		this.protocol = protocol;
	}

	public void setActivity(ProtocolActivity activity) {
		this.activity = activity;
	}

	public void setOrdinal(int order) {
		this.ordinal = order;
	}
	
	
}
