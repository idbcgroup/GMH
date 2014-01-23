package org.fourgeeks.gha.webclient.client.activity;

import java.util.ArrayList;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.MaintenanceActivity;

/**
 * 
 * @author caparicio
 * 
 */
public class ActivityUtil {

	/**
	 * 
	 */
	private ActivityUtil() {
		throw new UnsupportedOperationException(
				"Esta clase no debe ser instanciada");
	}

	public static ActivityRecord toGridRecord(
			MaintenanceActivity maintenanceActivity) {
		return new ActivityRecord(maintenanceActivity);
	}

	public static List<ActivityRecord> toGridRecords(
			List<MaintenanceActivity> maintenanceActivities) {
		List<ActivityRecord> list = new ArrayList<ActivityRecord>();
		for (MaintenanceActivity activity : maintenanceActivities) {
			list.add(new ActivityRecord(activity));
		}
		return list;
	}

}