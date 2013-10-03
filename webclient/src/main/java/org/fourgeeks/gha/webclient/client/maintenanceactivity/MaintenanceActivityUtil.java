/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;

/**
 * @author jfuentes, emiliot
 *
 */
public class MaintenanceActivityUtil {

	/**
	 * 
	 */
	private MaintenanceActivityUtil() {
		throw new UnsupportedOperationException("Esta clase no debe ser instanciada");
	}

	public static MaintenanceActivityGridRecord toGridRecord(MaintenanceActivity maintenanceActivity){
		return new MaintenanceActivityGridRecord(maintenanceActivity);
	}


	public static List<MaintenanceActivityGridRecord> toGridRecords(List<MaintenanceActivity> maintenanceActivities){
		List<MaintenanceActivityGridRecord> list = new ArrayList<MaintenanceActivityGridRecord>();
		for(MaintenanceActivity act : maintenanceActivities){
			list.add(new MaintenanceActivityGridRecord(act));
		}
		return list;
	}


}
