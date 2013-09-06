package org.fourgeeks.gha.webclient.client.UI.formItems;

import com.smartgwt.client.widgets.form.fields.TextItem;

public class GHATextItem extends TextItem {

	public GHATextItem() {
		super();
		setTextBoxStyle("input");
		setHeight(18);
		setCellStyle("gha-form-cell");
		setWidth(100);
		setTitleStyle("input-title");
	}

	public GHATextItem(String title, int width) {
		this(title);
		setWidth(width);
	}
	
	public GHATextItem(String title, int width, boolean active) {
		this(title);
		setWidth(width);
		setDisabled(!active);
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
	}
	
	
}