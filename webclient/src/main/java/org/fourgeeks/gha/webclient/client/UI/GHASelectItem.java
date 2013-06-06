package org.fourgeeks.gha.webclient.client.UI;

import com.smartgwt.client.widgets.form.fields.SelectItem;

public class GHASelectItem extends SelectItem {

	public GHASelectItem() {
		super();
		setControlStyle("margin-right");
		setTextBoxStyle("select");
		setHeight(15);
		setWidth(100);
		setTitleStyle("input-title");
	}

	public GHASelectItem(String title) {
		this();
		setTitle(title);
	}
}