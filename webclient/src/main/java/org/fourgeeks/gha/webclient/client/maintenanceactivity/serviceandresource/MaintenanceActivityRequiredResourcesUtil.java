package org.fourgeeks.gha.webclient.client.maintenanceactivity.serviceandresource;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.RequiredResources;

/**
 * 
 * @author caparicio
 * 
 */
public class MaintenanceActivityRequiredResourcesUtil {

	/**
	 * 
	 */
	private MaintenanceActivityRequiredResourcesUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * Transform a list of {@link RequiredResources} to a list of
	 * {@link MaintenanceActivityRequiredResourcesRecord}
	 * 
	 * @param entities
	 * @return a list of {@link MaintenanceActivityRequiredResourcesRecord}
	 */
	public static List<MaintenanceActivityRequiredResourcesRecord> toGridRecordsList(
			List<RequiredResources> entities) {
		List<MaintenanceActivityRequiredResourcesRecord> list = new ArrayList<MaintenanceActivityRequiredResourcesRecord>();
		for (RequiredResources entity : entities) {
			MaintenanceActivityRequiredResourcesRecord record = new MaintenanceActivityRequiredResourcesRecord(
					entity);
			list.add(record);
		}
		return list;
	}

	/**
	 * @param entities
	 *            Transform a list of {@link RequiredResources} to an array of
	 *            {@link MaintenanceActivityRequiredResourcesRecord}
	 * @return an array of {@link MaintenanceActivityRequiredResourcesRecord}
	 */
	public static MaintenanceActivityRequiredResourcesRecord[] toGridRecordsArray(
			List<RequiredResources> entities) {
		List<MaintenanceActivityRequiredResourcesRecord> recordsList = toGridRecordsList(entities);

		MaintenanceActivityRequiredResourcesRecord[] array = recordsList
				.toArray(new MaintenanceActivityRequiredResourcesRecord[] {});

		return array;
	}
}
