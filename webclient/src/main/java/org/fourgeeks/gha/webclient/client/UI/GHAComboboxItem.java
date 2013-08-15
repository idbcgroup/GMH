package org.fourgeeks.gha.webclient.client.UI;

import com.smartgwt.client.widgets.form.fields.ComboBoxItem;

public class GHAComboboxItem extends ComboBoxItem {

	public GHAComboboxItem() {
		super();
		setTextBoxStyle("select");
		setHeight(18);
		setCellStyle("gha-form-cell");
		setWidth(100);
		setTitleStyle("input-title");
		setShowFocused(false);
	}

	public GHAComboboxItem(String title, int width) {
		this(title);
		setWidth(width);
	}
	
	public GHAComboboxItem(String title, int width, boolean active) {
		this(title);
		setWidth(width);
		setDisabled(!active);
	}

	public GHAComboboxItem(String title) {
		this();
		setTitle(title);
	}

	public GHAComboboxItem(String title, boolean enabled) {
		this(title);
		setDisabled(!enabled);
	}

	@Override
	public void setDisabled(Boolean disabled) {
		super.setDisabled(disabled);
	}
}