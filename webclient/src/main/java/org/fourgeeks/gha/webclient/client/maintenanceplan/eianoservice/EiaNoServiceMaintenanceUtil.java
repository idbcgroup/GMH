/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.eianoservice;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;

/**
 * @author caparicio
 * 
 */
public class EiaNoServiceMaintenanceUtil {

	/**
	 * 
	 */
	private EiaNoServiceMaintenanceUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * 
	 * @param eiaEntities
	 * @return list
	 */
	public static List<EiaNoServiceMaintenanceRecord> toEiaNoServiceMaintenancePlanGridRecords(
			List<Eia> eiaEntities) {
		List<EiaNoServiceMaintenanceRecord> list = new ArrayList<EiaNoServiceMaintenanceRecord>();

		for (int i = 0; i < eiaEntities.size(); i++) {
			EiaNoServiceMaintenanceRecord record = new EiaNoServiceMaintenanceRecord(
					eiaEntities.get(i));
			record.setEiaNoServiceMaintenancePlanAttributes();
			list.add(record);
		}
		return list;
	}
}
