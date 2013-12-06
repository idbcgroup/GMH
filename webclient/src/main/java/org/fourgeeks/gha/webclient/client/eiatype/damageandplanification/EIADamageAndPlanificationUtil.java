package org.fourgeeks.gha.webclient.client.eiatype.damageandplanification;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaDamageReport;
import org.fourgeeks.gha.domain.gmh.EiaMaintenancePlanification;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenancePlanification;

import com.smartgwt.client.util.DateUtil;
import com.smartgwt.client.util.LogicalDate;
import com.smartgwt.client.util.LogicalTime;

/**
 * @author naramirez
 * 
 */
public class EIADamageAndPlanificationUtil {

	private EIADamageAndPlanificationUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	/**
	 * @param eiaDamageReportRecords
	 *            the list of {@link EiaDamageReport} entities
	 * @return A list of {@link EiaDamageReportRecord} whit the data contained
	 *         in the {@link EiaDamageReport} entities
	 */
	public static List<EiaDamageReportRecord> toDamageReportGridRecords(
			List<EiaDamageReport> eiaDamageReportRecords) {
		List<EiaDamageReportRecord> list = new ArrayList<EiaDamageReportRecord>();
		for (EiaDamageReport eia : eiaDamageReportRecords)
			list.add(new EiaDamageReportRecord(eia));
		return list;
	}

	/**
	 * @param preventivePlanif
	 *            the {@link EiaPreventiveMaintenancePlanification} entity
	 * @return A {@link EIAPreventiveMaintenancePlanificationRecord} whit the
	 *         data contained in the
	 *         {@link EiaPreventiveMaintenancePlanification} entity
	 */
	public static EIAPreventiveMaintenancePlanificationRecord toPreventiveMaintenanceGridRecord(
			EiaPreventiveMaintenancePlanification preventivePlanif) {
		return new EIAPreventiveMaintenancePlanificationRecord(preventivePlanif);
	}

	/**
	 * @param preventivePlanifs
	 *            the list of {@link EiaPreventiveMaintenancePlanification}
	 *            entities
	 * @return A list of {@link EIAPreventiveMaintenancePlanificationRecord}
	 *         whit the data contained in the
	 *         {@link EiaPreventiveMaintenancePlanification} entities
	 */
	public static List<EIAPreventiveMaintenancePlanificationRecord> toPreventiveMaintenanceGridRecords(
			List<EiaPreventiveMaintenancePlanification> preventivePlanifs) {
		List<EIAPreventiveMaintenancePlanificationRecord> list = new ArrayList<EIAPreventiveMaintenancePlanificationRecord>();

		for (EiaPreventiveMaintenancePlanification preventivePlanif : preventivePlanifs)
			list.add(new EIAPreventiveMaintenancePlanificationRecord(
					preventivePlanif));
		return list;
	}

	/**
	 * 
	 * @param maintenancePlanif
	 *            the {@link EiaMaintenancePlanification} entity
	 * @return A {@link EIAMaintenancePlanificationRecord} whit the data
	 *         contained in the {@link EiaMaintenancePlanification} entity
	 */
	public static EIAMaintenancePlanificationRecord toMaintenanceGridRecord(
			EiaMaintenancePlanification maintenancePlanif) {
		return new EIAMaintenancePlanificationRecord(maintenancePlanif);
	}

	/**
	 * 
	 * @param maintenancePlanifs
	 *            the list of {@link EiaMaintenancePlanification} entities
	 * @return A list of {@link EIAMaintenancePlanificationRecord} whit the data
	 *         contained in the {@link EiaMaintenancePlanification} entities
	 */
	public static List<EIAMaintenancePlanificationRecord> toMaintenanceGridRecords(
			List<EiaMaintenancePlanification> maintenancePlanifs) {
		List<EIAMaintenancePlanificationRecord> list = new ArrayList<EIAMaintenancePlanificationRecord>();

		for (EiaMaintenancePlanification entity : maintenancePlanifs)
			list.add(new EIAMaintenancePlanificationRecord(entity));
		return list;
	}

	/**
	 * 
	 * @param date
	 *            a {@link java.util.Date}
	 * @return a {@link java.sql.Date} with only the year-month-day
	 *         representative values and the hour:minute:seconds in 00:00:00
	 */
	public static Date getLogicalDate(java.util.Date date) {
		long time = DateUtil.getLogicalDateOnly(date).getTime();
		return new Date(time);
	}

	/**
	 * Combine a {@link LogicalDate} and a {@link LogicalTime} to obtain a
	 * {@link java.sql.Date} that represent a Datetime
	 * 
	 * @param date
	 *            a {@link LogicalDate}
	 * @param time
	 *            a {@link LogicalTime}
	 * @return a {@link java.sql.Date} object that represent a <i>Datetime</i>
	 */
	public static Timestamp getTimestamp(LogicalDate date, LogicalTime time) {
		if (date == null)
			return null;

		Timestamp timestamp = new Timestamp(DateUtil.combineLogicalDateAndTime(
				date, time).getTime());

		return timestamp;
	}
}