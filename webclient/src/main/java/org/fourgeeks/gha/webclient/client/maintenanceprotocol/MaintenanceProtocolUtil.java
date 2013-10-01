/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceprotocol;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;

/**
 * @author jfuentes
 *
 */
public class MaintenanceProtocolUtil {

	/**
	 * 
	 */
	private MaintenanceProtocolUtil() {
		throw new UnsupportedOperationException("Esta clase no debe ser instanciada");
	}
	
	public static MaintenanceProtocolRecord toGridRecord(MaintenanceProtocol maintenanceProtocol){
		return new MaintenanceProtocolRecord(maintenanceProtocol);
	}
	public static List<MaintenanceProtocolRecord> toGridRecords(List<MaintenanceProtocol> maintenanceProtocols){
		List<MaintenanceProtocolRecord> list = new ArrayList<MaintenanceProtocolRecord>();
		for(MaintenanceProtocol prot : maintenanceProtocols){
			list.add(new MaintenanceProtocolRecord(prot));
		}
		return list;
	}
	
}
