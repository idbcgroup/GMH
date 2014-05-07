package org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.eialistplanification;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaPlanificationEntity;

public class EiaPlanificationEntityUtil {

	private EiaPlanificationEntityUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	public static EiaListMaintenanceRecord toGridRecord(
			EiaPlanificationEntity entity) {
		return new EiaListMaintenanceRecord(entity);
	}

	public static List<EiaListMaintenanceRecord> toGridRecords(
			List<EiaPlanificationEntity> eiasEntities) {
		List<EiaListMaintenanceRecord> list = new ArrayList<EiaListMaintenanceRecord>();
		for (EiaPlanificationEntity eiaEntity : eiasEntities)
			list.add(new EiaListMaintenanceRecord(eiaEntity));
		return list;
	}
}