package org.fourgeeks.gha.webclient.client.eiapreventivemaintenanceplanification;

import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;

/**
 * @author alacret
 * 
 */
public interface PreventivePlanificationSelectionListener {
	/**
	 * @param maintenancePlanif
	 */
	public void select(EiaPreventiveMaintenancePlanification preventivePlanif);
}