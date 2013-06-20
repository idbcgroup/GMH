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

	public GHATextItem(String title, int width) {
		this(title);
		setWidth(width);
	}

	public GHATextItem(String title) {
		this();
		setTitle(title);
	}

	public GHATextItem(String title, boolean active) {
		this(title);
		setDisabled(!active);
	}

	@Override
	public void setDisabled(Boolean disabled) {
		super.setDisabled(disabled);
		setTextBoxStyle("input input-disabled");
		setTitleStyle("input-title");
	}
}