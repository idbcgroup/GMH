package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import java.util.LinkedHashMap;

import org.fourgeeks.gha.domain.enu.MaintenancePlanCancelationOption;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author naramirez
 */
public class GHAMaintenancePlanCancelationOptionSelectItem extends
		GHASelectItem {

	/** */
	public GHAMaintenancePlanCancelationOptionSelectItem() {
		super(GHAStrings.get("cancelation-option"));

		setValueMap(toValueMap());
		setDefaultValue(MaintenancePlanCancelationOption.UNDEFERRABLE.name());
	}

	/**
	 * @param required
	 * @param changedHandler
	 */
	public GHAMaintenancePlanCancelationOptionSelectItem(boolean required,
			ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param width
	 */
	public GHAMaintenancePlanCancelationOptionSelectItem(int width) {
		this();
		setWidth(width);
	}

	/**
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHAMaintenancePlanCancelationOptionSelectItem(int width,
			boolean required, ChangedHandler changedHandler) {
		this(width);
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @return a valueMap with this Enum values
	 */
	private LinkedHashMap<String, String> toValueMap() {
		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
		for (MaintenancePlanCancelationOption type : MaintenancePlanCancelationOption
				.values()) {
			final String key = type.name().toLowerCase();
			valueMap.put(type.name() + "", GHAStrings.get(key));
		}

		return valueMap;
	}
}
