package org.fourgeeks.gha.webclient.client.UI.formItems;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

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

	/**
	 * @param title
	 * @param width
	 */
	public GHANameItem(String title, int width, boolean required) {
		this(title, width);
		setRequired(required);
	}

	/**
	 * @param title
	 * @param width
	 */
	public GHANameItem(String title, boolean required) {
		super(title);
		setLength(20);
		setMask(">A<AAAAAAAAAAAAAAAAAAA");
		setRequired(required);
	}

	public GHANameItem(String title, boolean required,
			ChangedHandler changedHandler) {
		this(title, required);
		addChangedHandler(changedHandler);
	}
}
