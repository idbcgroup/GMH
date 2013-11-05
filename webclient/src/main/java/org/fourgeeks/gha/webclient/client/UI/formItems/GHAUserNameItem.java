package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

/**
 * @author alacret
 * 
 */
public class GHAUserNameItem extends GHATextItem {

	/**
	 * @param width
	 */
	public GHAUserNameItem(int width) {
		super(GHAStrings.get("user"), width);
		setLength(20);
		setMask("AAAAAAAAAAAAAAAAAAAA");
	}
}