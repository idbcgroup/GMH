package org.fourgeeks.gha.webclient.client.UI.formItems;

import com.smartgwt.client.widgets.form.fields.SelectItem;

public class GHASelectItem extends SelectItem {

	public GHASelectItem() {
		super();
		setControlStyle("margin-right");
		setTextBoxStyle("select");
		setHeight(18);
		setCellStyle("gha-form-cell");
		setWidth(100);
		setTitleStyle("input-title");
		setShowFocused(false);
	}

	public GHASelectItem(String title, int width) {
		this(title);
		setWidth(width);
	}
	
	public GHASelectItem(String title, int width, boolean active) {
		this(title);
		setWidth(width);
		setDisabled(!active);
	}

	public GHASelectItem(int width) {
		this();
		setShowTitle(false);
		setWidth(width);
	}
	
	public GHASelectItem(String title) {
		this();
		setTitle(title);
	}

	public GHASelectItem(String title, boolean enabled) {
		this(title);
		setDisabled(!enabled);
	}

	@Override
	public void setDisabled(Boolean disabled) {
		super.setDisabled(disabled);
	}
}