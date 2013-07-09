package org.fourgeeks.gha.webclient.client.UI;

import com.smartgwt.client.widgets.form.fields.DateItem;

public class GHADateItem extends DateItem {

	public GHADateItem() {
		super();
		setTextBoxStyle("input-dateItem");
		setHeight(20);
		setWidth(100);
		setTitleStyle("input-title");
		setUseTextField(true); 
	}

	public GHADateItem(String title) {
		this();
		setTitle(title);
	}
}