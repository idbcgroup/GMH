/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.subprotocolactivities;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.Eia;

/**
 * @author caparicio
 * 
 */
public class MaintenanceSubprotocolActivitiesUtil {

	/**
	 * 
	 */
	private MaintenanceSubprotocolActivitiesUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * 
	 * @param eiaEntities
	 * @return list
	 */
	public static List<MaintenanceSubprotocolActivitiesRecord> toEiaNoServiceMaintenancePlanGridRecords(
			List<Eia> eiaEntities) {
		List<MaintenanceSubprotocolActivitiesRecord> list = new ArrayList<MaintenanceSubprotocolActivitiesRecord>();

		for (int i = 0; i < eiaEntities.size(); i++) {
			MaintenanceSubprotocolActivitiesRecord record = new MaintenanceSubprotocolActivitiesRecord(
					eiaEntities.get(i));
			record.setEiaNoServiceMaintenancePlanAttributes();
			list.add(record);
		}
		return list;
	}
}
