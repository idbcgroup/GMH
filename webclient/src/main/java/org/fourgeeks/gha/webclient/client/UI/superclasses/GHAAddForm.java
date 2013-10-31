package org.fourgeeks.gha.webclient.client.UI.superclasses;


/**
 * @author alacret
 * 
 */
public abstract class GHAAddForm extends GHASlideInWindow {
	private GHALabel label;

	/**
	 * @param title
	 */
	public GHAAddForm(String title) {
		super();
		label = new GHALabel(title);
		addMember(label);
	}
}