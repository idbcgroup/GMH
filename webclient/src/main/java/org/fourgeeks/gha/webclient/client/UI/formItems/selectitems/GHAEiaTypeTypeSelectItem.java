package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import org.fourgeeks.gha.domain.enu.EiaTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author alacret A select item with the mobility items
 */
public class GHAEiaTypeTypeSelectItem extends GHASelectItem {
	public static final String labelKey = "eiatype-type";

	/**
	 * 
	 */
	public GHAEiaTypeTypeSelectItem() {
		super(GHAStrings.get("eiatype-type"));
		setValueMap(EiaTypeEnum.toValueMap());
	}
	
	/**
	 * @param width
	 * 
	 */
	public GHAEiaTypeTypeSelectItem(int width) {
		this();
		setWidth(width);
	}

	/**
	 * @param width
	 * @param required
	 * @param changedHandler
	 * 
	 */
	public GHAEiaTypeTypeSelectItem(int width, boolean required,
			ChangedHandler changedHandler) {
		super(width);
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param required
	 * @param changedHandler
	 */
	public GHAEiaTypeTypeSelectItem(boolean required,
			ChangedHandler changedHandler) {
		this();
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	// public EiaTypeEnum getValue() {
	// if (getValue() == null)
	// return null;
	// else
	// return EiaTypeEnum.valueOf(getValueAsString());
	// }
}
