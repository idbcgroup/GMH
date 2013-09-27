/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan;

import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan.GWTMaintenancePlanService;
import org.fourgeeks.gha.webclient.client.eiatype.maintenance.plan.GWTMaintenancePlanServiceAsync;

import com.google.gwt.core.shared.GWT;

/**
 * @author emiliot
 *
 */
public class MaintenancePlanModel {
	private static final GWTMaintenancePlanServiceAsync service = GWT.create(GWTMaintenancePlanService.class);
	
	private MaintenancePlanModel(){
		throw new UnsupportedOperationException("Esta clase no debe ser instanciada");
	}
	
	public static void getAll(GHAAsyncCallback<List<MaintenancePlan>> callback){
		service.getAll(callback);
	}
	
	public static void save(MaintenancePlan maintenancePlan, GHAAsyncCallback<MaintenancePlan> callback){
		service.save(maintenancePlan, callback);
	}
	
	public static void find(MaintenancePlan maintenancePlan, GHAAsyncCallback<List<MaintenancePlan>> callback){
		service.find(maintenancePlan, callback);
	}
}
