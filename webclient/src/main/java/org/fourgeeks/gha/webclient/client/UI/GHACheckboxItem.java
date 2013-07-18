package org.fourgeeks.gha.webclient.client.UI;

import com.smartgwt.client.widgets.form.fields.CheckboxItem;

public class GHACheckboxItem extends CheckboxItem {

	public GHACheckboxItem() {
		super();
		setHeight(15);
		setShowTitle(false);
		setTitleStyle("input-title");
	}

	public GHACheckboxItem(String title) {
		this();
		setTitle(title);
	}
}