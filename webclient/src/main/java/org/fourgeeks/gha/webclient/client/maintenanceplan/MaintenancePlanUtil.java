/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenancePlan;

/**
 * @author emiliot
 *
 */
public class MaintenancePlanUtil {

	/**
	 * 
	 */
	private MaintenancePlanUtil() {
		throw new UnsupportedOperationException("Esta clase no debe ser instanciada");
	}
	public static MaintenancePlanRecord toGridRecord(MaintenancePlan maintenancePlan){
		return new MaintenancePlanRecord(maintenancePlan);
	}
	public static List<MaintenancePlanRecord> toGridRecords(List<MaintenancePlan> maintenancePlans){
		List<MaintenancePlanRecord> list = new ArrayList<MaintenancePlanRecord>();
		for(MaintenancePlan plan : maintenancePlans){
			list.add(new MaintenancePlanRecord(plan));
		}
		return list;
	}
	
}
