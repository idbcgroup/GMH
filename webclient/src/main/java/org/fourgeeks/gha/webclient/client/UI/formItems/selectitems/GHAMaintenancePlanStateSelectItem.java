package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

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
		setValueMap(MaintenancePlanState.toValueMap());
	}

	/**
	 * @param width
	 */
	public GHAMaintenancePlanStateSelectItem(int width) {
		this();
		setWidth(width);
	}

	/**
	 * @param title
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
	 * @param title
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