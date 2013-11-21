package org.fourgeeks.gha.webclient.client.eiatype.eiapreventivemaintenanceplanitification;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;

import com.smartgwt.client.util.DateUtil;

public class EIAPreventivePlanifUtil {

	private EIAPreventivePlanifUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	public static EIAPreventiveMaintenancePlanificationRecord toGridRecord(
			EiaPreventiveMaintenancePlanification preventivePlanif) {
		return new EIAPreventiveMaintenancePlanificationRecord(preventivePlanif);
	}

	public static List<EIAPreventiveMaintenancePlanificationRecord> toGridRecords(
			List<EiaPreventiveMaintenancePlanification> preventivePlanifs) {
		List<EIAPreventiveMaintenancePlanificationRecord> list = new ArrayList<EIAPreventiveMaintenancePlanificationRecord>();

		for (EiaPreventiveMaintenancePlanification preventivePlanif : preventivePlanifs)
			list.add(new EIAPreventiveMaintenancePlanificationRecord(
					preventivePlanif));
		return list;
	}

	public static Date getLogicalDate(java.util.Date date) {
		long time = DateUtil.getLogicalDateOnly(date).getTime();
		return new Date(time);
	}
}