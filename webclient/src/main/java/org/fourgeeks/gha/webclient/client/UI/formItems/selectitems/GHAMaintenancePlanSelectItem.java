package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.fourgeeks.gha.domain.gmh.EiaType;
import org.fourgeeks.gha.domain.gmh.EiaTypeMaintenancePlan;
import org.fourgeeks.gha.domain.gmh.MaintenancePlan;
import org.fourgeeks.gha.webclient.client.UI.GHAAsyncCallback;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;
import org.fourgeeks.gha.webclient.client.maintenanceplan.asociatedeiatype.EiaTypeMaintenancePlanModel;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author nelson
 * 
 */
public class GHAMaintenancePlanSelectItem extends GHASelectItem {
	private HashMap<String, MaintenancePlan> entities;

	{
		entities = new HashMap<String, MaintenancePlan>();
	}

	/**
	 * @param required
	 * @param changedHandler
	 */
	public GHAMaintenancePlanSelectItem(boolean required,
			ChangedHandler changedHandler) {
		super("Plan de mantenimiento", required, changedHandler);
	}

	/**
	 * 
	 */
	public GHAMaintenancePlanSelectItem() {
		super("Plan de mantenimiento");
	}

	/**
	 * 
	 * @param eiaType
	 */
	public void fillByEiaType(EiaType eiaType) {
		EiaTypeMaintenancePlanModel.findByEiaType(eiaType,
				new GHAAsyncCallback<List<EiaTypeMaintenancePlan>>() {
					@Override
					public void onSuccess(
							final List<EiaTypeMaintenancePlan> result) {
						entities = new HashMap<String, MaintenancePlan>();
						final LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

						for (EiaTypeMaintenancePlan plan : result) {
							MaintenancePlan mPlan = plan.getMaintenancePlan();
							String id = String.valueOf(plan.getId());
							map.put(id, mPlan.getName());
							entities.put(id, mPlan);
						}
						setValueMap(map);
					}
				});
	}

	/**
	 * @return the {@link MaintenancePlan} entity of the selected value, or null
	 *         if none value has been selected
	 */
	public MaintenancePlan getValueAsMaintenancePlan() {
		String id = getValueAsString();
		return id != null ? entities.get(id) : null;
	}
}
