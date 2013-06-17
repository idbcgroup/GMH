package org.fourgeeks.gha.webclient.client.UI;

import com.smartgwt.client.widgets.form.fields.TextItem;

public class GHATextItem extends TextItem {

	public GHATextItem() {
		super();
		setTextBoxStyle("input");
		setHeight(15);
		setWidth(100);
		setTitleStyle("input-title");
	}

	public GHATextItem(String title) {
		this();
		setTitle(title);
	}

	public GHATextItem(String title, boolean active) {
		this(title);
		setShowDisabled(false);
		setDisabled(!active);

	}
}