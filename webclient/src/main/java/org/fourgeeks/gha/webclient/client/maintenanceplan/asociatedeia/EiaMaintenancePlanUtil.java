/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeia;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;

/**
 * @author emiliot
 * 
 */
public class EiaMaintenancePlanUtil {

	/**
	 * 
	 */
	private EiaMaintenancePlanUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	//
	// /**
	// *
	// * @param entity
	// * @return record
	// */
	// public static EiaMaintenancePlanRecord toEiaMaintenancePlanGridRecord(
	// Eia entity) {
	// EiaMaintenancePlanRecord record = new EiaMaintenancePlanRecord(entity);
	// record.setEiaMaintenancePlanAttributes();
	// return record;
	// }

	/**
	 * 
	 * @param eiaEntities 
	 * @param empEntities 
	 * @return list
	 */
	public static List<EiaMaintenancePlanRecord> toEiaMaintenancePlanGridRecords(
			List<Eia> eiaEntities, List<EiaMaintenancePlanification> empEntities) {
		List<EiaMaintenancePlanRecord> list = new ArrayList<EiaMaintenancePlanRecord>();
		
		for (int i = 0; i < eiaEntities.size(); i++) {
			EiaMaintenancePlanRecord record = new EiaMaintenancePlanRecord(
					eiaEntities.get(i), empEntities.get(i));
			record.setEiaMaintenancePlanAttributes();
			list.add(record);
		}
		return list;
	}
}
