package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author alacret
 * 
 */
public class GHACodeItem extends GHATextItem {

	/**
	 * 
	 */
	public GHACodeItem() {
		this(GHAUiHelper.CODE_ITEM_LENGTH);
	}

	/**
	 * @param width
	 * 
	 */
	public GHACodeItem(int width) {
		super(GHAStrings.get("code"), width);
		setLength(GHAUiHelper.CODE_ITEM_LENGTH);
	}

	/**
	 * @param required
	 */
	public GHACodeItem(boolean required) {
		this();
		setRequired(required);
	}

	/**
	 * @param required
	 * @param width
	 */
	public GHACodeItem(boolean required, int width) {
		this(required);
		setWidth(width);
	}

	/**
	 * @param required
	 * @param width
	 * @param changeHandler
	 */
	public GHACodeItem(boolean required, int width, ChangedHandler changeHandler) {
		this(required, width);
		addChangedHandler(changeHandler);
	}

}