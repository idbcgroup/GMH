package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

/**
 * @author alacret A select item with the mobility items
 */
public class GHAMobilitySelectItem extends GHASelectItem {
	/**
	 * 
	 */
	public GHAMobilitySelectItem() {
		super(GHAStrings.get("mobility"));
		setValueMap(EiaMobilityEnum.toValueMap());
	}
	
	/**
	 * @param width
	 * 
	 */
	public GHAMobilitySelectItem(int width) {
		this();
		setWidth(width);
	}

	// public EiaMobilityEnum getValue() {
	// if (getValue() == null)
	// return null;
	// else
	// return EiaMobilityEnum.valueOf(getValueAsString());
	// }
}
