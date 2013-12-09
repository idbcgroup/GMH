package org.fourgeeks.gha.webclient.client.UI.formItems.textitems;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author alacret
 * 
 */
public class GHACodeTextItem extends GHATextItem {

	/**
	 * 
	 */
	public GHACodeTextItem() {
		super(GHAStrings.get("code"));
		setLength(GHAUiHelper.CODE_ITEM_LENGTH);
	}

	/**
	 * @param width
	 * 
	 */
	public GHACodeTextItem(int width) {
		super(GHAStrings.get("code"), width);
		setLength(GHAUiHelper.CODE_ITEM_LENGTH);
	}

	/**
	 * @param required
	 */
	public GHACodeTextItem(boolean required) {
		this();
		setRequired(required);
	}

	/**
	 * @param required
	 * @param width
	 */
	public GHACodeTextItem(boolean required, int width) {
		this(width);
		setRequired(required);
	}

	/**
	 * @param required
	 * @param width
	 * @param changeHandler
	 */
	public GHACodeTextItem(boolean required, int width, ChangedHandler changeHandler) {
		this(required, width);
		addChangedHandler(changeHandler);
	}
	
	/**
	 * @param required
	 * @param changeHandler
	 */
	public GHACodeTextItem(boolean required, ChangedHandler changeHandler) {
		this(required);
		addChangedHandler(changeHandler);
	}

}