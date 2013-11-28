package org.fourgeeks.gha.webclient.client.UI.formItems.textitems;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author alacret
 * 
 */
public class GHAUserNameTextItem extends GHATextItem {

	/**
	 * @param width
	 */
	public GHAUserNameTextItem(int width) {
		super(GHAStrings.get("user"), width);
		setLength(20);
		setMask("AAAAAAAAAAAAAAAAAAAA");
	}

	/**
	 * @param width
	 */
	public GHAUserNameTextItem() {
		super(GHAStrings.get("user"));
		setLength(20);
		setMask("AAAAAAAAAAAAAAAAAAAA");
	}

	/**
	 * @param width
	 */
	public GHAUserNameTextItem(int width, boolean required) {
		this(width);
		setRequired(required);
	}

	/**
	 * @param width
	 */
	public GHAUserNameTextItem(boolean required) {
		this();
		setRequired(required);
	}

	/**
	 * @param width
	 */
	public GHAUserNameTextItem(int width, boolean required,
			ChangedHandler changedHandler) {
		this(width, required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param width
	 */
	public GHAUserNameTextItem(boolean required, ChangedHandler changedHandler) {
		this(required);
		addChangedHandler(changedHandler);
	}
}