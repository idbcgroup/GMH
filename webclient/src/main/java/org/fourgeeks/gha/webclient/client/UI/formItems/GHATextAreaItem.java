package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.form.fields.TextAreaItem;

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
		setTextBoxStyle("input");
		setCellStyle("gha-form-cell");
		setWidth(GHAUiHelper.DEFAULT_ITEM_SIZE);
		setTitleStyle("input-title");
		setLength(255);
		setHeight(GHAUiHelper.DEFAULT_TEXT_AREA_ITEM_HEIGHT);
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

}
