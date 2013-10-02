package org.fourgeeks.gha.webclient.client.UI.formItems;

import com.smartgwt.client.widgets.form.fields.RadioGroupItem;

public class GHARadioGroupItem extends RadioGroupItem {

	public GHARadioGroupItem() {
		super();

		setHeight(18);
		setWidth(200);
		setShowTitle(false);
		setVertical(false);
	}

	public GHARadioGroupItem(int width) {
		this();
		setWidth(width);
	}

	public GHARadioGroupItem(int width, boolean isVertical) {
		this();
		setWidth(width);
		setVertical(isVertical);
	}
}
