package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import java.util.LinkedHashMap;

import org.fourgeeks.gha.domain.enu.MaintenancePlanType;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author jfuentes A select item with the Document Types
 * 
 */
public class GHAMaintenancePlanTypeSelectItem extends GHASelectItem {
	/**
	 * @return a valueMap to fill the select
	 */
	public static LinkedHashMap<String, String> getValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (MaintenancePlanType value : MaintenancePlanType.values()) {
			String key = value.name().toLowerCase();
			valueMap.put(value.name() + "", GHAStrings.get(key));
		}
		return valueMap;
	}

	/**
	 * 
	 */
	public GHAMaintenancePlanTypeSelectItem() {
		super(GHAStrings.get("plan-type"));
		setValueMap(getValueMap());
	}

	/**
	 * @param required
	 * @param changedHandler
	 */
	public GHAMaintenancePlanTypeSelectItem(boolean required,
			ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param width
	 */
	public GHAMaintenancePlanTypeSelectItem(int width) {
		this();
		setWidth(width);
	}

	/**
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHAMaintenancePlanTypeSelectItem(int width, boolean required,
			ChangedHandler changedHandler) {
		this(width);
		setRequired(required);
		addChangedHandler(changedHandler);
	}

}