package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author alacret
 * 
 */
public class GHATextAreaItem extends TextAreaItem {

	/**
	 * 
	 */
	public GHATextAreaItem() {
		super();
		setHeight(GHAUiHelper.DEFAULT_TEXT_AREA_ITEM_HEIGHT);
		setTextBoxStyle("input");
		setCellStyle("gha-form-cell");
		setWidth("*");
		setTitleStyle("input-title");
		setLength(255);
	}

	/**
	 * @param name
	 * @param width
	 */
	public GHATextAreaItem(String name, int width) {
		this();
		setTitle(name);
		setWidth(width);
	}

	/**
	 * @param name
	 * @param width
	 * @param changedHandler
	 */
	public GHATextAreaItem(String name, int width, ChangedHandler changedHandler) {
		this();
		setTitle(name);
		setWidth(width);
		addChangedHandler(changedHandler);
	}

}
