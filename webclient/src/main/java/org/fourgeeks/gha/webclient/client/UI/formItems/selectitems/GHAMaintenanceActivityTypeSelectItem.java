package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import org.fourgeeks.gha.domain.enu.MaintenanceActivityTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author naramirez
 */
public class GHAMaintenanceActivityTypeSelectItem extends GHASelectItem {

	/** */
	public GHAMaintenanceActivityTypeSelectItem() {
		super(GHAStrings.get("type"));
		setValueMap(MaintenanceActivityTypeEnum.toValueMap());
	}

	/**
	 * @param required
	 * @param changedHandler
	 */
	public GHAMaintenanceActivityTypeSelectItem(boolean required,
			ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param active
	 */
	public GHAMaintenanceActivityTypeSelectItem(boolean active) {
		this();
		setDisabled(!active);
	}
}
