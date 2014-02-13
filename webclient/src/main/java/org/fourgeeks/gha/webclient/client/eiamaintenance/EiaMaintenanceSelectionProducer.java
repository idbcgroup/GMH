package org.fourgeeks.gha.webclient.client.eiamaintenance;

import org.fourgeeks.gha.domain.gmh.EiaMaintenance;

/**
 * @author naramirez
 */
public interface EiaMaintenanceSelectionProducer {
	/**
	 * @param listener
	 */
	public void addEiaMaintenanceSelectionListener(
			EiaMaintenanceSelectionListener listener);

	/**
	 * @param listener
	 */
	public void removeEiaMaintenanceSelectionListener(
			EiaMaintenanceSelectionListener listener);

	/**
	 * @param entity
	 */
	public void notifyEiaMaintenance(EiaMaintenance entity);

}