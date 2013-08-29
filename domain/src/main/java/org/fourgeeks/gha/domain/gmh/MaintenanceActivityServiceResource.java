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
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "maintenanceActivityFk", "serviceResourceFk" }))
public class MaintenanceActivityServiceResource extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "maintenanceActivityFk", nullable = false)
	private MaintenanceActivity maintenanceActivity;
	
	@ManyToOne
	@JoinColumn(name = "serviceResourceFk", nullable = false)
	private ServiceResource serviceResource;

	/**
	 * 
	 */
	public MaintenanceActivityServiceResource() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param activity
	 * @param resource2
	 */
	public MaintenanceActivityServiceResource(MaintenanceActivity activity,
			ServiceResource serviceResource) {
		this.maintenanceActivity = activity;
		this.serviceResource = serviceResource;
	}

	public MaintenanceActivity getProtocolActivity() {
		return maintenanceActivity;
	}

	public ServiceResource getServiceResource() {
		return serviceResource;
	}

	public void setProtocolActivity(MaintenanceActivity activity) {
		this.maintenanceActivity = activity;
	}

	public void setServiceResource(ServiceResource serviceResource) {
		this.serviceResource = serviceResource;
	}
	
	
}
