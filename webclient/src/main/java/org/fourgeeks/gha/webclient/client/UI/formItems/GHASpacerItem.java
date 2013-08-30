package org.fourgeeks.gha.webclient.client.UI.formItems;

import com.smartgwt.client.widgets.form.fields.SpacerItem;

public class GHASpacerItem extends SpacerItem {

	public GHASpacerItem() {
		super();
	}

	public GHASpacerItem(int colspan) {
		this();
		setColSpan(colspan);
	}
}