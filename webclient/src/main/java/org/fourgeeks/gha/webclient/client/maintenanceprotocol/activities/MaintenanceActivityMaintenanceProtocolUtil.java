package org.fourgeeks.gha.webclient.client.maintenanceprotocol.activities;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivityMaintenanceProtocol;

/**
 * @author emiliot
 * 
 */
public class MaintenanceActivityMaintenanceProtocolUtil {
	/**
	 * 
	 */
	private MaintenanceActivityMaintenanceProtocolUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	public static MaintenanceActivityMaintenanceProtocolGridRecord toGridRecord(
			MaintenanceActivityMaintenanceProtocol entity) {
		return new MaintenanceActivityMaintenanceProtocolGridRecord(entity);
	}

	public static List<MaintenanceActivityMaintenanceProtocolGridRecord> toGridRecords(
			List<MaintenanceActivityMaintenanceProtocol> entities) {
		List<MaintenanceActivityMaintenanceProtocolGridRecord> list = new ArrayList<MaintenanceActivityMaintenanceProtocolGridRecord>();
		for (MaintenanceActivityMaintenanceProtocol entity : entities) {
			list.add(new MaintenanceActivityMaintenanceProtocolGridRecord(
					entity));
		}
		return list;
	}
}
