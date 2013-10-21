package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.domain.enu.EiaSubTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

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
	 * 
	 */
	public GHAEiaTypeSubTypeSelectItem() {
		this(GHAUiHelper.DEFAULT_ITEM_SIZE);
	}

	// public EiaSubTypeEnum getValue() {
	// if (getValue() == null)
	// return null;
	// else
	// return EiaSubTypeEnum.valueOf(getValueAsString());
	// }
}
