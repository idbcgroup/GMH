package org.fourgeeks.gha.webclient.client.eiatype.damageandplanification;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;

import com.smartgwt.client.util.DateUtil;

public class EIADamageAndPlanificationUtil {

	private EIADamageAndPlanificationUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	public static EIAPreventiveMaintenancePlanificationRecord toPreventiveMaintenanceGridRecord(
			EiaPreventiveMaintenancePlanification preventivePlanif) {
		return new EIAPreventiveMaintenancePlanificationRecord(preventivePlanif);
	}

	public static List<EIAPreventiveMaintenancePlanificationRecord> toPreventiveMaintenanceGridRecords(
			List<EiaPreventiveMaintenancePlanification> preventivePlanifs) {
		List<EIAPreventiveMaintenancePlanificationRecord> list = new ArrayList<EIAPreventiveMaintenancePlanificationRecord>();

		for (EiaPreventiveMaintenancePlanification preventivePlanif : preventivePlanifs)
			list.add(new EIAPreventiveMaintenancePlanificationRecord(
					preventivePlanif));
		return list;
	}

	public static EIAMaintenancePlanificationRecord toMaintenanceGridRecord(
			EiaMaintenancePlanification preventivePlanif) {
		return new EIAMaintenancePlanificationRecord(preventivePlanif);
	}

	public static List<EIAMaintenancePlanificationRecord> toMaintenanceGridRecords(
			List<EiaMaintenancePlanification> entities) {
		List<EIAMaintenancePlanificationRecord> list = new ArrayList<EIAMaintenancePlanificationRecord>();

		for (EiaMaintenancePlanification entity : entities)
			list.add(new EIAMaintenancePlanificationRecord(entity));
		return list;
	}

	public static Date getLogicalDate(java.util.Date date) {
		long time = DateUtil.getLogicalDateOnly(date).getTime();
		return new Date(time);
	}
}