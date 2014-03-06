package org.fourgeeks.gha.webclient.client.UI.formItems.textitems;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;

/**
 * @author alacret
 * 
 */
public class GHAAgeTextItem extends GHATextItem {

	/**
	 * @param text
	 */
	public GHAAgeTextItem(String text) {
		super(GHAStrings.get("age"));
		setValue(text);
		setDisabled(true);
	}

	/**
	 * @param text
	 * @param width
	 */
	public GHAAgeTextItem(String text, int width) {
		this(text);
		setWidth(width);
	}
}
