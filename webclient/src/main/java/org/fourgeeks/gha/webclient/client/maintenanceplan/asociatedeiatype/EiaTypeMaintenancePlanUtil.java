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
	public static EiaTypeMaintenancePlanRecord toEiaTypeGridRecord(EiaTypeMaintenancePlan entity){
		EiaTypeMaintenancePlanRecord record = new EiaTypeMaintenancePlanRecord(entity);
		record.setEiaTypeAttributes();
		return record;
	}

	public static List<EiaTypeMaintenancePlanRecord> toEiaTypeGridRecords(List<EiaTypeMaintenancePlan> entities){
		List<EiaTypeMaintenancePlanRecord> list = new ArrayList<EiaTypeMaintenancePlanRecord>();
		for(EiaTypeMaintenancePlan entity : entities){
			EiaTypeMaintenancePlanRecord record = new EiaTypeMaintenancePlanRecord(entity);
			record.setEiaTypeAttributes();
			list.add(record);
		}
		return list;
	}
	
	public static EiaTypeMaintenancePlanRecord toMaintenancePlanGridRecord(EiaTypeMaintenancePlan entity){
		EiaTypeMaintenancePlanRecord record = new EiaTypeMaintenancePlanRecord(entity);
		record.setMaintenancePlanAttributes();
		return record;
	}

	public static List<EiaTypeMaintenancePlanRecord> toMaintenancePlanGridRecords(List<EiaTypeMaintenancePlan> entities){
		List<EiaTypeMaintenancePlanRecord> list = new ArrayList<EiaTypeMaintenancePlanRecord>();
		for(EiaTypeMaintenancePlan entity : entities){
			
			EiaTypeMaintenancePlanRecord record = new EiaTypeMaintenancePlanRecord(entity);
			record.setMaintenancePlanAttributes();
			list.add(record);
		}
		return list;
	}
}
