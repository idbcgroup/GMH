package org.fourgeeks.gha.webclient.client.eiamaintenance;

import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;

/**
 * @author alacret
 * 
 */
public interface EiaMaintenanceSelectionListener {
	/**
	 * @param entity
	 */
	public void select(EiaMaintenancePlanification entity);
}