/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceprotocol;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan.GWTMaintenancePlanService;
import org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol.GWTMaintenanceProtocolServiceAsync;

import com.google.gwt.core.shared.GWT;

/**
 * @author emiliot
 *
 */
public class MaintenanceProtocolModel {
	private static final GWTMaintenanceProtocolServiceAsync service = GWT.create(GWTMaintenancePlanService.class);

	/**
	 * 
	 */
	private MaintenanceProtocolModel() {
		throw new UnsupportedOperationException("Esta clase no debe ser instanciada");
	}
	
	public static void getAll(GHAAsyncCallback<List<MaintenanceProtocol>> callback){
		service.getAll(callback);
	}
	public static void save(MaintenanceProtocol maintenanceProtocol, GHAAsyncCallback<MaintenanceProtocol> callback){
		service.save(maintenanceProtocol, callback);
	}
	public static void update(MaintenanceProtocol maintenanceProtocol, GHAAsyncCallback<MaintenanceProtocol> callback){
		service.update(maintenanceProtocol, callback);
	}
	public static void find(MaintenanceProtocol maintenanceProtocol, GHAAsyncCallback<List<MaintenanceProtocol>> callback){
		service.find(maintenanceProtocol, callback);
	}
}
