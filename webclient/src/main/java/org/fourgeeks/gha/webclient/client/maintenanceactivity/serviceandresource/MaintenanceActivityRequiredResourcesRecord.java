package org.fourgeeks.gha.webclient.client.maintenanceactivity.serviceandresource;

import org.fourgeeks.gha.domain.glm.Material;
import org.fourgeeks.gha.domain.gmh.EiaType;
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

	/**
	 * 
	 * @param requiredR
	 */
	public MaintenanceActivityRequiredResourcesRecord(
			RequiredResources requiredR) {
		this.requiredR = requiredR;
		ServiceAndResource resource = requiredR.getResource();

		if (resource instanceof EiaType) {
			setAttribute("code", ((EiaType) resource).getCode());
			setAttribute("name", ((EiaType) resource).getName());
		} else if (resource instanceof Material) {
			setAttribute("code", ((Material) resource).getCode());
			setAttribute("name", ((Material) resource).getName());
		}
		setAttribute("quantity", requiredR.getQuantity());
	}

	@Override
	public ServiceAndResource toEntity() {
		return requiredR.getResource();
	}

	/**
	 * 
	 * @return requiredR
	 */
	public RequiredResources toRequiredResourcesEntity() {
		return requiredR;
	}
}
