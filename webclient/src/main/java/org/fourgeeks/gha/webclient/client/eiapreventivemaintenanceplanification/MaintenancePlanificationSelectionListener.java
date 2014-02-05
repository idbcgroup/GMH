package org.fourgeeks.gha.webclient.client.eiapreventivemaintenanceplanification;

import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;

/**
 * @author alacret
 * 
 */
public interface MaintenancePlanificationSelectionListener {
	/**
	 * @param maintenancePlanif
	 */
	public void select(EiaMaintenancePlanification maintenancePlanification);
}