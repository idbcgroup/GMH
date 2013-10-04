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
	
	public static MaintenancePlanMaintenanceProtocolGridRecord toPlanRecord(MaintenancePlanMaintenanceProtocol entity){
		MaintenancePlanMaintenanceProtocolGridRecord record = new MaintenancePlanMaintenanceProtocolGridRecord(entity);
		record.setMaintenancePlanAttributes();
		return record;
	}
	
	public static List<MaintenancePlanMaintenanceProtocolGridRecord> toPlanRecords(List<MaintenancePlanMaintenanceProtocol> entities){
		List<MaintenancePlanMaintenanceProtocolGridRecord> list = new ArrayList<MaintenancePlanMaintenanceProtocolGridRecord>();
		for(MaintenancePlanMaintenanceProtocol entity : entities){
			MaintenancePlanMaintenanceProtocolGridRecord record = new MaintenancePlanMaintenanceProtocolGridRecord(entity);
			record.setMaintenancePlanAttributes();
			list.add(record);
		}
		return list;
	}
	
	public static MaintenancePlanMaintenanceProtocolGridRecord toProtocolRecord(MaintenancePlanMaintenanceProtocol entity){
		MaintenancePlanMaintenanceProtocolGridRecord record = new MaintenancePlanMaintenanceProtocolGridRecord(entity);
		record.setMaintenanceProtocolAttributes();
		return record;
	}
	
	public static List<MaintenancePlanMaintenanceProtocolGridRecord> toProtocolRecords(List<MaintenancePlanMaintenanceProtocol> entities){
		List<MaintenancePlanMaintenanceProtocolGridRecord> list = new ArrayList<MaintenancePlanMaintenanceProtocolGridRecord>();
		for(MaintenancePlanMaintenanceProtocol entity : entities){
			MaintenancePlanMaintenanceProtocolGridRecord record = new MaintenancePlanMaintenanceProtocolGridRecord(entity);
			record.setMaintenanceProtocolAttributes();
			list.add(record);
		}
		return list;
	}
}
