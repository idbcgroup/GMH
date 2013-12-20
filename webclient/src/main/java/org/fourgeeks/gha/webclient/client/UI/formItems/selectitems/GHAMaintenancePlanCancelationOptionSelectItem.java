package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

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
	public static final String labelKey = "cancelation-option";

	/** */
	public GHAMaintenancePlanCancelationOptionSelectItem() {
		super(GHAStrings.get(labelKey));
		setValueMap(MaintenancePlanCancelationOption.toValueMap());
		setDefaultValue(MaintenancePlanCancelationOption.NOT_DEFERRABLE.name());
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
	 * @param required
	 * @param changedHandler
	 */
	public GHAMaintenancePlanCancelationOptionSelectItem(boolean required,
			ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
	}
}
