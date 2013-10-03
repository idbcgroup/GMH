/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceplan.maintenanceprotocol;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenancePlanMaintenanceProtocol;

/**
 * @author emiliot
 *
 */
public class MaintenancePlanMaintenanceProtocolUtil {

	/**
	 * 
	 */
	private MaintenancePlanMaintenanceProtocolUtil() {
		throw new UnsupportedOperationException("Esta clase no debe ser instanciada");
	}
	
	public static MaintenancePlanMaintenanceProtocolRecord toPlanRecord(MaintenancePlanMaintenanceProtocol entity){
		MaintenancePlanMaintenanceProtocolRecord record = new MaintenancePlanMaintenanceProtocolRecord(entity);
		record.setMaintenancePlanAttributes();
		return record;
	}
	
	public static List<MaintenancePlanMaintenanceProtocolRecord> toPlanRecords(List<MaintenancePlanMaintenanceProtocol> entities){
		List<MaintenancePlanMaintenanceProtocolRecord> list = new ArrayList<MaintenancePlanMaintenanceProtocolRecord>();
		for(MaintenancePlanMaintenanceProtocol entity : entities){
			MaintenancePlanMaintenanceProtocolRecord record = new MaintenancePlanMaintenanceProtocolRecord(entity);
			record.setMaintenancePlanAttributes();
			list.add(record);
		}
		return list;
	}
	
	public static MaintenancePlanMaintenanceProtocolRecord toProtocolRecord(MaintenancePlanMaintenanceProtocol entity){
		MaintenancePlanMaintenanceProtocolRecord record = new MaintenancePlanMaintenanceProtocolRecord(entity);
		record.setMaintenanceProtocolAttributes();
		return record;
	}
	
	public static List<MaintenancePlanMaintenanceProtocolRecord> toProtocolRecords(List<MaintenancePlanMaintenanceProtocol> entities){
		List<MaintenancePlanMaintenanceProtocolRecord> list = new ArrayList<MaintenancePlanMaintenanceProtocolRecord>();
		for(MaintenancePlanMaintenanceProtocol entity : entities){
			MaintenancePlanMaintenanceProtocolRecord record = new MaintenancePlanMaintenanceProtocolRecord(entity);
			record.setMaintenanceProtocolAttributes();
			list.add(record);
		}
		return list;
	}
}
