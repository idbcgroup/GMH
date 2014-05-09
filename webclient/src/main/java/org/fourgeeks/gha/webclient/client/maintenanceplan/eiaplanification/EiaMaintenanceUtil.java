/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.eiaplanification;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;

/**
 * @author emiliot
 * 
 */
public class EiaMaintenanceUtil {

	/**
	 * 
	 */
	private EiaMaintenanceUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * 
	 * @param eiaEntities
	 * @param empEntities
	 * @return list
	 */
	public static List<EiaMaintenanceRecord> toEiaMaintenancePlanGridRecords(
			List<Eia> eiaEntities, List<EiaMaintenancePlanification> empEntities) {
		List<EiaMaintenanceRecord> list = new ArrayList<EiaMaintenanceRecord>();

		for (int i = 0; i < eiaEntities.size(); i++) {
			EiaMaintenanceRecord record = new EiaMaintenanceRecord(
					eiaEntities.get(i), empEntities.get(i));
			record.setEiaMaintenancePlanAttributes();
			list.add(record);
		}
		return list;
	}
}
