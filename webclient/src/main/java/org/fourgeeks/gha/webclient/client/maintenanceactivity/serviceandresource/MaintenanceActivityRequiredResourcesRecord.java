package org.fourgeeks.gha.webclient.client.maintenanceactivity.serviceandresource;

import org.fourgeeks.gha.domain.gmh.RequiredResources;
import org.fourgeeks.gha.domain.gmh.ServiceAndResource;
import org.fourgeeks.gha.webclient.client.UI.grids.GHAGridRecord;

/**
 * 
 * @author caparicio
 * 
 */
public class MaintenanceActivityRequiredResourcesRecord extends
		GHAGridRecord<ServiceAndResource> {

	private RequiredResources requiredR;
	private ServiceAndResource resource;

	/**
	 * 
	 * @param requiredR
	 * @param resource
	 */
	public MaintenanceActivityRequiredResourcesRecord(
			RequiredResources requiredR, ServiceAndResource resource) {
		this.requiredR = requiredR;
		this.resource = resource;

	}

	@Override
	public ServiceAndResource toEntity() {
		return resource;
	}
}
