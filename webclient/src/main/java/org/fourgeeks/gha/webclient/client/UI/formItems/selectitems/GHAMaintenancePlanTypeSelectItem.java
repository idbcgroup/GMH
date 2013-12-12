package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

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
	 * 
	 */
	public GHAMaintenancePlanTypeSelectItem() {
		super(GHAStrings.get("plan-type"));
		setValueMap(MaintenancePlanType.toValueMap());
	}

	/**
	 * @param width
	 */
	public GHAMaintenancePlanTypeSelectItem(int width) {
		this();
		setWidth(width);
	}
	
	/**
	 * @param title
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
	 * @param title
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