package org.fourgeeks.gha.webclient.client.UI.formItems.textitems;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author alacret
 * 
 */
public class GHANameTextItem extends GHATextItem {

	/**
	 * @param title
	 */
	public GHANameTextItem() {
		super(GHAStrings.get("name"));
		setLength(20);
		setMask(">A<AAAAAAAAAAAAAAAAAAA");
	}
	
	/**
	 * @param title
	 */
	public GHANameTextItem(String title) {
		super(title);
		setLength(20);
		setMask(">A<AAAAAAAAAAAAAAAAAAA");
	}
	
	/**
	 * @param title
	 * @param width
	 */
	public GHANameTextItem(String title, int width) {
		this(title);
		setWidth(width);
	}

	/**
	 * @param title
	 * @param width
	 */
	public GHANameTextItem(String title, int width, boolean required) {
		this(title, width);
		setRequired(required);
	}

	/**
	 * @param title
	 * @param width
	 */
	public GHANameTextItem(String title, boolean required) {
		this(title);
		setRequired(required);
	}

	public GHANameTextItem(String title, boolean required,
			ChangedHandler changedHandler) {
		this(title, required);
		addChangedHandler(changedHandler);
	}
}