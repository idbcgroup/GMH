package org.fourgeeks.gha.webclient.client.eiamaintenance;

import java.util.List;

import org.fourgeeks.gha.domain.ess.MaintenanceServiceOrder;
import org.fourgeeks.gha.domain.gmh.EiaCorrectiveMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaPreventiveMaintenance;
import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author naramirez
 */
public class EiaMaintenanceModel {
	private static final GWTEiaMaintenanceServiceAsync service = GWT
			.create(GWTEiaMaintenanceService.class);

	/**
	 * @param eiaType
	 * @param callback
	 */
	public static void find(EiaType eiaType,
			GHAAsyncCallback<List<EiaMaintenance>> callback) {

		service.find(eiaType, callback);
	}

	/**
	 * @param eiaMaintenance
	 * @param callback
	 */
	public static void findServiceOrder(EiaMaintenance eiaMaintenance,
			GHAAsyncCallback<MaintenanceServiceOrder> callback) {

		service.findServiceOrder(eiaMaintenance, callback);
	}

	/**
	 * @param entity
	 * @param callback
	 */
	public static void saveCorrectiveMaintenance(
			EiaCorrectiveMaintenance entity,
			AsyncCallback<EiaCorrectiveMaintenance> callback) {

		service.saveCorrectiveMaintenance(entity, callback);
	}

	/**
	 * @param entity
	 * @param callback
	 */
	public static void savePreventiveMaintenance(
			EiaPreventiveMaintenance entity,
			AsyncCallback<EiaPreventiveMaintenance> callback) {

		service.savePreventiveMaintenance(entity, callback);
	}

	/**
	 * @param entity
	 * @param callback
	 */
	public static void updateCorrectiveMaintenance(
			EiaCorrectiveMaintenance entity,
			AsyncCallback<EiaCorrectiveMaintenance> callback) {

		service.updateCorrectiveMaintenance(entity, callback);
	}

	/**
	 * @param entity
	 * @param callback
	 */
	public static void updatePreventiveMaintenance(
			EiaPreventiveMaintenance entity,
			AsyncCallback<EiaPreventiveMaintenance> callback) {

		service.updatePreventiveMaintenance(entity, callback);
	}
}
