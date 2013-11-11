package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.form.fields.CheckboxItem;

public class GHACheckboxItem extends CheckboxItem {

	public GHACheckboxItem() {
		super();
		setHeight(20);
		setWidth(GHAUiHelper.DEFAULT_ITEM_SIZE);
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