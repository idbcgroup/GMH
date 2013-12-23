package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import java.util.LinkedHashMap;

import org.fourgeeks.gha.domain.enu.MaintenancePlanStatus;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author naramirez
 */
public class GHAMaintenancePlanStatusSelectItem extends GHASelectItem {
	/**
	 * 
	 */
	public GHAMaintenancePlanStatusSelectItem() {
		super(GHAStrings.get("plan-status"));

		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (MaintenancePlanStatus value : MaintenancePlanStatus.values()) {
			String key = value.name().toLowerCase();
			valueMap.put(value.name() + "", GHAStrings.get(key));
		}

		setValueMap(valueMap);
	}

	/**
	 * @param width
	 */
	public GHAMaintenancePlanStatusSelectItem(int width) {
		this();
		setWidth(width);
	}

	/**
	 * @param required
	 * @param changedHandler
	 */
	public GHAMaintenancePlanStatusSelectItem(boolean required,
			ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHAMaintenancePlanStatusSelectItem(int width, boolean required,
			ChangedHandler changedHandler) {
		this(width);
		setRequired(required);
		addChangedHandler(changedHandler);
	}
}
