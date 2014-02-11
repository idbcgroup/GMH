/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceactivity.subprotocol;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.SubProtocolAndChecklist;
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
	 * Transform a list of {@link SubProtocolAndChecklist} to a list of
	 * {@link MaintenanceActivitySubprotocolRecord}
	 * 
	 * @param entities
	 * @return a list of {@link MaintenanceProtocolsRecord}
	 */
	public static List<MaintenanceActivitySubprotocolRecord> toGridRecordsList(
			List<SubProtocolAndChecklist> entities) {
		List<MaintenanceActivitySubprotocolRecord> list = new ArrayList<MaintenanceActivitySubprotocolRecord>();
		for (SubProtocolAndChecklist entity : entities) {
			MaintenanceActivitySubprotocolRecord record = new MaintenanceActivitySubprotocolRecord(
					entity);
			list.add(record);
		}
		return list;
	}

	/**
	 * @param entities
	 *            Transform a list of {@link SubProtocolAndChecklist} to an
	 *            array of {@link MaintenanceActivitySubprotocolRecord}
	 * @return an array of {@link MaintenanceActivitySubprotocolRecord}
	 */
	public static MaintenanceActivitySubprotocolRecord[] toGridRecordsArray(
			List<SubProtocolAndChecklist> entities) {
		List<MaintenanceActivitySubprotocolRecord> recordsList = toGridRecordsList(entities);

		MaintenanceActivitySubprotocolRecord[] array = recordsList
				.toArray(new MaintenanceActivitySubprotocolRecord[] {});

		return array;
	}
}
