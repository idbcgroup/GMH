package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import java.util.LinkedHashMap;

import org.fourgeeks.gha.domain.enu.MaintenancePlanState;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author jfuentes A select item with the Document Types
 * 
 */
public class GHAMaintenancePlanStateSelectItem extends GHASelectItem {
	/**
	 * 
	 */
	public GHAMaintenancePlanStateSelectItem() {
		super(GHAStrings.get("plan-state"));

		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (MaintenancePlanState value : MaintenancePlanState.values()) {
			String key = value.name().toLowerCase();
			valueMap.put(value.name() + "", GHAStrings.get(key));
		}

		setValueMap(valueMap);
	}

	/**
	 * @param width
	 */
	public GHAMaintenancePlanStateSelectItem(int width) {
		this();
		setWidth(width);
	}

	/**
	 * @param required
	 * @param changedHandler
	 */
	public GHAMaintenancePlanStateSelectItem(boolean required,
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
	public GHAMaintenancePlanStateSelectItem(int width, boolean required,
			ChangedHandler changedHandler) {
		this(width);
		setRequired(required);
		addChangedHandler(changedHandler);
	}

}