package org.fourgeeks.gha.webclient.client.UI;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;

public class GHATitleTextItem extends StaticTextItem {

	public GHATitleTextItem() {
		super();
		setAlign(Alignment.LEFT);
		setVAlign(VerticalAlignment.BOTTOM);
		setHeight(18);
		setCellStyle("gha-form-cell");
		setShowTitle(false);
		setTextBoxStyle("titletext");
	}

	public GHATitleTextItem(String title) {
		this();
		setDefaultValue(title);
	}
}