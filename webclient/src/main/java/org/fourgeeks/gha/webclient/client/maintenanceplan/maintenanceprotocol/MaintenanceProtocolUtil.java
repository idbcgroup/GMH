/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;

/**
 * @author emiliot
 * 
 */
public class MaintenanceProtocolUtil {

	private MaintenanceProtocolUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * Transform a list of {@link MaintenanceProtocol} to a list of
	 * {@link MaintenanceProtocolRecord}
	 * 
	 * @param entities
	 * @return a list of {@link MaintenanceProtocolRecord}
	 */
	public static List<MaintenanceProtocolRecord> toGridRecordsList(
			List<MaintenanceProtocol> entities) {
		List<MaintenanceProtocolRecord> list = new ArrayList<MaintenanceProtocolRecord>();
		for (MaintenanceProtocol entity : entities) {
			MaintenanceProtocolRecord record = new MaintenanceProtocolRecord(
					entity);
			list.add(record);
		}
		return list;
	}

	/**
	 * @param entities
	 *            Transform a list of {@link MaintenanceProtocol} to an array
	 *            of {@link MaintenanceProtocolRecord}
	 * @return an array of {@link MaintenanceProtocolRecord}
	 */
	public static MaintenanceProtocolRecord[] toGridRecordsArray(
			List<MaintenanceProtocol> entities) {
		List<MaintenanceProtocolRecord> recordsList = toGridRecordsList(entities);

		MaintenanceProtocolRecord[] array = recordsList
				.toArray(new MaintenanceProtocolRecord[] {});

		return array;
	}
}
