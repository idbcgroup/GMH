package org.fourgeeks.gha.webclient.client.UI.formItems;

import com.smartgwt.client.widgets.form.fields.CheckboxItem;

public class GHACheckboxItem extends CheckboxItem {

	public GHACheckboxItem() {
		super();
		setHeight(20);
		setCellStyle("gha-form-cell");
		setShowTitle(false);
		setTitleStyle("input-title");
	}

	public GHACheckboxItem(String title) {
		this();
		setTitle(title);
	}
	
	public GHACheckboxItem(String title, boolean active) {
		this(title);
		setDisabled(!active);
	}
}