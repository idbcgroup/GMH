package org.fourgeeks.gha.webclient.client.maintenanceactivity;

import java.util.LinkedHashMap;

import org.fourgeeks.gha.domain.enu.MaintenanceActivityState;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author naramirez
 */
public class GHAMaintenanceActivityStateSelectItem extends GHASelectItem {

	/**
	 * 
	 */
	public GHAMaintenanceActivityStateSelectItem() {
		super(GHAStrings.get("activity-state"));

		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (MaintenanceActivityState value : MaintenanceActivityState.values()) {
			String key = value.name().toLowerCase();
			valueMap.put(value.name() + "", GHAStrings.get(key));
		}

		setValueMap(valueMap);
	}

	/**
	 * @param width
	 */
	public GHAMaintenanceActivityStateSelectItem(int width) {
		this();
		setWidth(width);
	}

	/**
	 * @param required
	 * @param changedHandler
	 */
	public GHAMaintenanceActivityStateSelectItem(boolean required,
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
	public GHAMaintenanceActivityStateSelectItem(int width, boolean required,
			ChangedHandler changedHandler) {
		this(width);
		setRequired(required);
		addChangedHandler(changedHandler);
	}
}
