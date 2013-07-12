package org.fourgeeks.gha.webclient.client.UI;

import com.smartgwt.client.widgets.form.fields.DateItem;

/**
 * @author alacret TODO
 */
public class GHADateItem extends DateItem {

	/**
	 * TODO
	 */
	public GHADateItem() {
		super();
		setTextBoxStyle("input-dateItem");
		setHeight(20);
		setWidth(100);
		setTitleStyle("input-title");
		setUseTextField(true);
	}

	/**
	 * TODO
	 * 
	 * @param title
	 */
	public GHADateItem(String title) {
		this();
		setTitle(title);
	}
}