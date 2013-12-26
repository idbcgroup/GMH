/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocols;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceProtocols;

/**
 * @author emiliot
 * 
 */
public class MaintenanceProtocolsUtil {

	private MaintenanceProtocolsUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * Transform a list of {@link MaintenanceProtocols} to a list of
	 * {@link MaintenanceProtocolsRecord}
	 * 
	 * @param entities
	 * @return a list of {@link MaintenanceProtocolsRecord}
	 */
	public static List<MaintenanceProtocolsRecord> toGridRecordsList(
			List<MaintenanceProtocols> entities) {
		List<MaintenanceProtocolsRecord> list = new ArrayList<MaintenanceProtocolsRecord>();
		for (MaintenanceProtocols entity : entities) {
			MaintenanceProtocolsRecord record = new MaintenanceProtocolsRecord(
					entity);
			list.add(record);
		}
		return list;
	}

	/**
	 * @param entities
	 *            Transform a list of {@link MaintenanceProtocols} to an array
	 *            of {@link MaintenanceProtocolsRecord}
	 * @return an array of {@link MaintenanceProtocolsRecord}
	 */
	public static MaintenanceProtocolsRecord[] toGridRecordsArray(
			List<MaintenanceProtocols> entities) {
		List<MaintenanceProtocolsRecord> recordsList = toGridRecordsList(entities);

		MaintenanceProtocolsRecord[] array = recordsList
				.toArray(new MaintenanceProtocolsRecord[] {});

		return array;
	}
}
