package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.domain.enu.EiaMobilityEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

/**
 * @author alacret A select item with the mobility items
 */
public class GHAMobilitySelectItem extends GHASelectItem {

	/**
	 * @param width
	 * 
	 */
	public GHAMobilitySelectItem(int width) {
		super(GHAStrings.get("mobility"), width);
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
