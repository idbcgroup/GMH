package org.fourgeeks.gha.webclient.client.UI.formItems;

import com.smartgwt.client.types.TimeDisplayFormat;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.TimeItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

public class GHATimeItem extends TimeItem {

	public GHATimeItem() {
		super();
		setUseTextField(true);
		setTimeFormatter(TimeDisplayFormat.TOSHORTPADDED24HOURTIME);

		TextItem textItemProperties = new TextItem();
		textItemProperties.setHeight(20);
		textItemProperties.setWidth("*");
		textItemProperties.setTextBoxStyle("input");
		setTextFieldProperties(textItemProperties);

		setHeight(20);
		setWidth("*");
		setCellStyle("gha-form-cell");
		setTitleStyle("input-title");

		setShowHint(false);
		setUseMask(true);
	}

	/**
	 * @param title
	 */
	public GHATimeItem(String title) {
		this();
		setTitle(title);
	}

	public GHATimeItem(String title, boolean active) {
		this(title);
		setDisabled(!active);
	}

	/**
	 * @param title
	 */
	public GHATimeItem(String title, ChangedHandler changeHandler) {
		this();
		setTitle(title);
		addChangedHandler(changeHandler);
	}
}
