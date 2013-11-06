package org.fourgeeks.gha.webclient.client.UI.formItems;

/**
 * @author alacret
 * 
 */
public class GHANameItem extends GHATextItem {

	/**
	 * @param title
	 * @param width
	 */
	public GHANameItem(String title, int width) {
		super(title, width);
		setLength(20);
		setMask(">A<AAAAAAAAAAAAAAAAAAA");
	}
}
