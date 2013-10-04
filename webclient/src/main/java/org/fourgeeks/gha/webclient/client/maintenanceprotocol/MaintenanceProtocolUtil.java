/**
 * 
 */
package org.fourgeeks.gha.webclient.client.maintenanceprotocol;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceProtocol;

/**
 * @author jfuentes, emiliot
 *
 */
public class MaintenanceProtocolUtil {

	/**
	 * 
	 */
	private MaintenanceProtocolUtil() {
		throw new UnsupportedOperationException("Esta clase no debe ser instanciada");
	}

	public static MaintenanceProtocolGridRecord toGridRecord(MaintenanceProtocol maintenanceProtocol){
		return new MaintenanceProtocolGridRecord(maintenanceProtocol);
	}
	public static List<MaintenanceProtocolGridRecord> toGridRecords(List<MaintenanceProtocol> maintenanceProtocols){
		List<MaintenanceProtocolGridRecord> list = new ArrayList<MaintenanceProtocolGridRecord>();
		for(MaintenanceProtocol prot : maintenanceProtocols){
			list.add(new MaintenanceProtocolGridRecord(prot));
		}
		return list;
	}

}
