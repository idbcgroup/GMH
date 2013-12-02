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
		setWidth("*");

		setOriginalStyle();
		setLength(255);
	}

	/**
	 * 
	 */
	private void setOriginalStyle() {
		setTextBoxStyle("input");
		setCellStyle("gha-form-cell");
		setTitleStyle("input-title");
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
	
	/**
	 * @param name
	 * @param changedHandler
	 */
	public GHATextAreaItem(String name, ChangedHandler changedHandler) {
		this();
		setTitle(name);
		addChangedHandler(changedHandler);
	}

}
