package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author alacret A select item with the mobility items
 */
public class GHAEiaTypeSubTypeSelectItem extends GHASelectItem {

	/**
	 * @param width
	 * 
	 */
	public GHAEiaTypeSubTypeSelectItem(int width) {
		super(GHAStrings.get("subtype"), width);
		setValueMap(EiaSubTypeEnum.toValueMap());
	}

	/**
	 * @param width
	 * @param changedHandler
	 * 
	 */
	public GHAEiaTypeSubTypeSelectItem(int width, ChangedHandler changedHandler) {
		super(GHAStrings.get("subtype"), width);
		setValueMap(EiaSubTypeEnum.toValueMap());
		addChangedHandler(changedHandler);
	}
	
	/**
	 * @param changedHandler
	 * 
	 */
	public GHAEiaTypeSubTypeSelectItem(ChangedHandler changedHandler) {
		super(GHAStrings.get("subtype"));
		setValueMap(EiaSubTypeEnum.toValueMap());
		addChangedHandler(changedHandler);
	}

	/**
	 * 
	 */
	public GHAEiaTypeSubTypeSelectItem() {
		super(GHAStrings.get("subtype"));
		setValueMap(EiaSubTypeEnum.toValueMap());
	}

	// public EiaSubTypeEnum getValue() {
	// if (getValue() == null)
	// return null;
	// else
	// return EiaSubTypeEnum.valueOf(getValueAsString());
	// }
}
