package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.domain.enu.UserLogonStatusEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

/**
 * @author alacret A select item with the mobility items
 */
public class GHAUserStateSelectItem extends GHASelectItem {

	/**
	 * @param width
	 * 
	 */
	public GHAUserStateSelectItem(int width) {
		super(GHAStrings.get("state"), width);
		setValueMap(UserLogonStatusEnum.toValueMap());
	}

	/**
	 * 
	 */
	public GHAUserStateSelectItem() {
		this(GHAUiHelper.DEFAULT_ITEM_SIZE);
	}

}