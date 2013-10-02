/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;

/**
 * @author jfuentes
 *
 */
public class MaintenanceActivityUtil {

	/**
	 * 
	 */
	private MaintenanceActivityUtil() {
		throw new UnsupportedOperationException("Esta clase no debe ser instanciada");
	}
	
	public static MaintenanceActivityRecord toGridRecord(MaintenanceActivity maintenanceActivity){
		return new MaintenanceActivityRecord(maintenanceActivity);
	}
	
	
	public static List<MaintenanceActivityRecord> toGridNodes(List<MaintenanceActivity> maintenanceActivities){
//		TODO: Traer la lista de todas las Activities para el gris
		List<MaintenanceActivityRecord> list = new ArrayList<MaintenanceActivityRecord>();
		for(MaintenanceActivity act : maintenanceActivities){
			list.add(new MaintenanceActivityRecord(act));
		}
		return list;
	}
	

}
