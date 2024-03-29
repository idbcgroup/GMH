package org.fourgeeks.gha.webclient.client.maintenanceactivity.subprotocol;

/**
 * 
 * @author caparicio
 * 
 */
public interface MaintenanceActivitySubProtocolProducer {
	/**
	 * 
	 * @param listener
	 */
	public void addMaintenanceActivitySubProtocolListener(MaintenanceActivitySubProtocolListener listener);

	/**
	 * 
	 * @param listener
	 */
	public void removeMaintenanceActivitySubProtocolListener(MaintenanceActivitySubProtocolListener listener);

	/**
 * 
 */
	public void notifyMaintenanceActivitySubProtocolSubTabs();
}