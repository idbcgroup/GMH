package org.fourgeeks.gha.webclient.client.UI.formItems;

import com.smartgwt.client.widgets.form.fields.CheckboxItem;

public class GHACheckboxItem extends CheckboxItem {

	public GHACheckboxItem() {
		super();
		setHeight(20);
		setWidth("*");
		
		setShowTitle(false);
		setCellStyle("gha-form-cell");
		setTitleStyle("input-title");
		
		// setErrorOrientation(FormErrorOrientation.RIGHT);
		setShowErrorIcon(false);
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