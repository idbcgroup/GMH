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
 * ManyToMany between Resources and ProtocolActivities
 *
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "protocolActivityFk", "rasFk" }))
public class ProtocolActivityRaS extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "protocolActivityFk", nullable = false)
	private ProtocolActivity protocolActivity;
	
	@ManyToOne
	@JoinColumn(name = "rasFk", nullable = false)
	private RaS ras;

	/**
	 * 
	 */
	public ProtocolActivityRaS() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param activity
	 * @param resource2
	 */
	public ProtocolActivityRaS(ProtocolActivity activity,
			RaS ras) {
		this.protocolActivity = activity;
		this.ras = ras;
	}

	public ProtocolActivity getProtocolActivity() {
		return protocolActivity;
	}

	public RaS getRaS() {
		return ras;
	}

	public void setProtocolActivity(ProtocolActivity protocolActivity) {
		this.protocolActivity = protocolActivity;
	}

	public void setRaS(RaS resource) {
		this.ras = resource;
	}
	
	
}
