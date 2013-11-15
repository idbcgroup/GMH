package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

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

	/**
	 * @param width
	 */
	public GHAUserNameItem() {
		super(GHAStrings.get("user"));
		setLength(20);
		setMask("AAAAAAAAAAAAAAAAAAAA");
	}

	/**
	 * @param width
	 */
	public GHAUserNameItem(int width, boolean required) {
		this(width);
		setRequired(required);
	}

	/**
	 * @param width
	 */
	public GHAUserNameItem(boolean required) {
		this();
		setRequired(required);
	}

	/**
	 * @param width
	 */
	public GHAUserNameItem(int width, boolean required,
			ChangedHandler changedHandler) {
		this(width, required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param width
	 */
	public GHAUserNameItem(boolean required, ChangedHandler changedHandler) {
		this(required);
		addChangedHandler(changedHandler);
	}
}