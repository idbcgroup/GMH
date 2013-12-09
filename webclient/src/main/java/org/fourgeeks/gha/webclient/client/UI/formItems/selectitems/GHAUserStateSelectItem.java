package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import org.fourgeeks.gha.domain.enu.UserLogonStatusEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

/**
 * @author alacret A select item with the mobility items
 */
public class GHAUserStateSelectItem extends GHASelectItem {
	public static final String labelKey = "state";
	
	/**
	 * @param width
	 * 
	 */
	public GHAUserStateSelectItem(int width) {
		super(GHAStrings.get(labelKey), width);
		setValueMap(UserLogonStatusEnum.toValueMap());
	}

	/**
	 * 
	 */
	public GHAUserStateSelectItem() {
		super(GHAStrings.get(labelKey));
		setValueMap(UserLogonStatusEnum.toValueMap());
	}

}