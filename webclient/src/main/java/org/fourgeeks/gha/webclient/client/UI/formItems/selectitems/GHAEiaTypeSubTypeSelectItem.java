package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author alacret A select item with the mobility items
 */
public class GHAEiaTypeSubTypeSelectItem extends GHASelectItem {
	public static final String labelKey = "eiatype-subtype";
	
	/**
	 * 
	 */
	public GHAEiaTypeSubTypeSelectItem() {
		super(GHAStrings.get("eiatype-subtype"));
		setValueMap(EiaSubTypeEnum.toValueMap());
	}
	
	/**
	 * @param width
	 * 
	 */
	public GHAEiaTypeSubTypeSelectItem(int width) {
		this();
		setWidth(width);
	}

	/**
	 * @param changedHandler
	 * 
	 */
	public GHAEiaTypeSubTypeSelectItem(ChangedHandler changedHandler) {
		this();
		addChangedHandler(changedHandler);
	}
	
	/**
	 * @param width
	 * @param changedHandler
	 * 
	 */
	public GHAEiaTypeSubTypeSelectItem(int width, ChangedHandler changedHandler) {
		this(width);
		addChangedHandler(changedHandler);
	}


	// public EiaSubTypeEnum getValue() {
	// if (getValue() == null)
	// return null;
	// else
	// return EiaSubTypeEnum.valueOf(getValueAsString());
	// }
}
