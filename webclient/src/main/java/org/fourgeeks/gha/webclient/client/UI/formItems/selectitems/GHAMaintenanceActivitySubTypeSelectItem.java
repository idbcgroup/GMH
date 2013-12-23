package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import org.fourgeeks.gha.domain.enu.MaintenanceActivitySubTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author naramirez
 */
public class GHAMaintenanceActivitySubTypeSelectItem extends GHASelectItem {
	/** */
	public GHAMaintenanceActivitySubTypeSelectItem() {
		super(GHAStrings.get("subtype"));
		setValueMap(MaintenanceActivitySubTypeEnum.toValueMap());
	}

	/**
	 * @param required
	 * @param changedHandler
	 */
	public GHAMaintenanceActivitySubTypeSelectItem(boolean required,
			ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param active
	 */
	public GHAMaintenanceActivitySubTypeSelectItem(boolean active) {
		this();
		setDisabled(!active);
	}
}
