package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

/**
 * @author alacret A select item with the mobility items
 */
public class GHAMobilitySelectItem extends GHASelectItem {
	public static final String labelKey = "mobility";
	
	/**
	 * @param width
	 * 
	 */
	public GHAMobilitySelectItem(int width) {
		super(GHAStrings.get(labelKey), width);
		setValueMap(EiaMobilityEnum.toValueMap());
	}

	/**
	 * 
	 */
	public GHAMobilitySelectItem() {
		this(GHAUiHelper.DEFAULT_ITEM_SIZE);
	}

	// public EiaMobilityEnum getValue() {
	// if (getValue() == null)
	// return null;
	// else
	// return EiaMobilityEnum.valueOf(getValueAsString());
	// }
}
