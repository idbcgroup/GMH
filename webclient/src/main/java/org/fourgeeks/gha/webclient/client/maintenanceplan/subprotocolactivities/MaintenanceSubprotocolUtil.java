/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.subprotocolactivities;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceSubProtocol;
import org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocols.MaintenanceProtocolsRecord;

/**
 * @author caparicio
 * 
 */
public class MaintenanceSubprotocolUtil {

	/**
	 * 
	 */
	private MaintenanceSubprotocolUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * Transform a list of {@link MaintenanceSubProtocol} to a list of
	 * {@link MaintenanceSubprotocolRecord}
	 * 
	 * @param entities
	 * @return a list of {@link MaintenanceProtocolsRecord}
	 */
	public static List<MaintenanceSubprotocolRecord> toGridRecordsList(
			List<MaintenanceSubProtocol> entities) {
		List<MaintenanceSubprotocolRecord> list = new ArrayList<MaintenanceSubprotocolRecord>();
		for (MaintenanceSubProtocol entity : entities) {
			MaintenanceSubprotocolRecord record = new MaintenanceSubprotocolRecord(
					entity);
			list.add(record);
		}
		return list;
	}

	/**
	 * @param entities
	 *            Transform a list of {@link MaintenanceSubProtocol} to an array
	 *            of {@link MaintenanceSubprotocolRecord}
	 * @return an array of {@link MaintenanceSubprotocolRecord}
	 */
	public static MaintenanceSubprotocolRecord[] toGridRecordsArray(
			List<MaintenanceSubProtocol> entities) {
		List<MaintenanceSubprotocolRecord> recordsList = toGridRecordsList(entities);

		MaintenanceSubprotocolRecord[] array = recordsList
				.toArray(new MaintenanceSubprotocolRecord[] {});

		return array;
	}
}
