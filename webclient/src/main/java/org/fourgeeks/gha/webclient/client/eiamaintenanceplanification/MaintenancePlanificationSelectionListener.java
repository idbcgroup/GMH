package org.fourgeeks.gha.webclient.client.eiamaintenanceplanification;

import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;

/**
 * @author alacret, naramirez
 * 
 */
public interface MaintenancePlanificationSelectionListener {
	/**
	 * @param planification
	 */
	public void select(EiaMaintenancePlanification planification);
}