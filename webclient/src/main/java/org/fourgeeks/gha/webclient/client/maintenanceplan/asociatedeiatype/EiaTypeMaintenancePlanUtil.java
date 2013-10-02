/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;

/**
 * @author emiliot
 *
 */
public class EiaTypeMaintenancePlanUtil {

	/**
	 * 
	 */
	private EiaTypeMaintenancePlanUtil() {
		throw new UnsupportedOperationException("Esta clase no debe ser instanciada");
	}
	public static EiaTypeMaintenancePlanRecord toGridRecord(EiaTypeMaintenancePlan entity){
		return new EiaTypeMaintenancePlanRecord(entity);
	}

	public static List<EiaTypeMaintenancePlanRecord> toGridRecors(List<EiaTypeMaintenancePlan> entities){
		List<EiaTypeMaintenancePlanRecord> list = new ArrayList<EiaTypeMaintenancePlanRecord>();
		for(EiaTypeMaintenancePlan entity : entities){
			list.add(new EiaTypeMaintenancePlanRecord(entity));
		}
		return list;
	}
}
