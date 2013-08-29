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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "protocolActivityFk", "serviceResourceFk" }))
public class ProtocolActivityServiceResource extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "protocolActivityFk", nullable = false)
	private ProtocolActivity protocolActivity;
	
	@ManyToOne
	@JoinColumn(name = "serviceResourceFk", nullable = false)
	private ServiceResource serviceResource;

	/**
	 * 
	 */
	public ProtocolActivityServiceResource() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param activity
	 * @param resource2
	 */
	public ProtocolActivityServiceResource(ProtocolActivity activity,
			ServiceResource serviceResource) {
		this.protocolActivity = activity;
		this.serviceResource = serviceResource;
	}

	public ProtocolActivity getProtocolActivity() {
		return protocolActivity;
	}

	public ServiceResource getServiceResource() {
		return serviceResource;
	}

	public void setProtocolActivity(ProtocolActivity protocolActivity) {
		this.protocolActivity = protocolActivity;
	}

	public void setServiceResource(ServiceResource resource) {
		this.serviceResource = resource;
	}
	
	
}
