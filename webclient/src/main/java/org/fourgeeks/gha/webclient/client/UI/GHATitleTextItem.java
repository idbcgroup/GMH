package org.fourgeeks.gha.webclient.client.UI;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;

public class GHATitleTextItem extends StaticTextItem {

	public GHATitleTextItem() {
		super();
		setAlign(Alignment.RIGHT);
		setVAlign(VerticalAlignment.BOTTOM);
		setHeight(20);
		setWidth(150);
		setShowTitle(false);
		setTextBoxStyle("titletext");
	}

	public GHATitleTextItem(String title) {
		this();
		setDefaultValue(title);
	}
}