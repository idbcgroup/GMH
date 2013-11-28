package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import org.fourgeeks.gha.domain.enu.EiaStateEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author emiliot A select item with the eia state items
 * 
 */
public class GHAEiaStateSelectItem extends GHASelectItem {
	public static final String labelKey = "state";

	/**
	 * 
	 */
	public GHAEiaStateSelectItem() {
		super(GHAStrings.get(labelKey));
		setValueMap(EiaStateEnum.toValueMap());
	}

	/**
	 * @param width
	 */
	public GHAEiaStateSelectItem(int width) {
		super(GHAStrings.get(labelKey), width);
		setValueMap(EiaStateEnum.toValueMap());
	}

	/**
	 * @param title
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHAEiaStateSelectItem(int width, boolean required,
			ChangedHandler changedHandler) {
		super(GHAStrings.get(labelKey), width);
		setRequired(required);
		addChangedHandler(changedHandler);
		setValueMap(EiaStateEnum.toValueMap());
	}

	/**
	 * @param title
	 * @param required
	 * @param changedHandler
	 */
	public GHAEiaStateSelectItem(boolean required, ChangedHandler changedHandler) {
		super(GHAStrings.get(labelKey));
		setRequired(required);
		addChangedHandler(changedHandler);
		setValueMap(EiaStateEnum.toValueMap());
	}

}
