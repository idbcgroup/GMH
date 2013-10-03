/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlanMaintenanceProtocol;
import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;

/**
 * @author emiliot
 *
 */
public class MaintenancePlanMaintenanceProtocolModel {
	private static final GWTMaintenancePlanMaintenanceProtocolServiceAsync service = GWT.create(GWTMaintenancePlanMaintenanceProtocolService.class);
	
	/**
	 * 
	 */
	private MaintenancePlanMaintenanceProtocolModel() {
		throw new UnsupportedOperationException("Esta clase no debe ser instanciada");
	}
	
	public static void delete(long id, GHAAsyncCallback<Void> callback){
		service.delete(id, callback);
	}
	
	public static void save(MaintenancePlanMaintenanceProtocol maintenancePlanMaintenanceProtocol, GHAAsyncCallback<MaintenancePlanMaintenanceProtocol> callback){
		service.save(maintenancePlanMaintenanceProtocol, callback);
	}
	
	public static void findByMaintenancePlan(MaintenancePlan maintenancePlan, GHAAsyncCallback< List<MaintenancePlanMaintenanceProtocol>> callback){
		service.findByMaintenancePlan(maintenancePlan, callback);
	}
	
	public static void findByMaintenanceProtocol(MaintenanceProtocol maintenanceProtocol, GHAAsyncCallback<List<MaintenancePlanMaintenanceProtocol>> callback){
		service.findByMaintenanceProtocol(maintenanceProtocol, callback);
	}
}
