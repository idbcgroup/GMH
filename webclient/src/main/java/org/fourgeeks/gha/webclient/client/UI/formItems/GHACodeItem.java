package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

/**
 * @author alacret
 * 
 */
public class GHACodeItem extends GHATextItem {

	/**
	 * 
	 */
	public GHACodeItem() {
		super(GHAStrings.get("code"));
		setLength(GHAUiHelper.CODE_ITEM_LENGTH);
	}

	/**
	 * @param width
	 * 
	 */
	public GHACodeItem(int width) {
		super(GHAStrings.get("code"), width);
		setLength(GHAUiHelper.CODE_ITEM_LENGTH);
	}

}