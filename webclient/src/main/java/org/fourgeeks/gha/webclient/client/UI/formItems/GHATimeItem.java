package org.fourgeeks.gha.webclient.client.UI.formItems;

import com.smartgwt.client.types.TimeDisplayFormat;
import com.smartgwt.client.util.LogicalTime;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.TimeItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

public class GHATimeItem extends TimeItem {

	public GHATimeItem() {
		super();
		setHeight(20);
		setWidth("*");

		setUseTextField(true);
		setTimeFormatter(TimeDisplayFormat.TOSHORTPADDED24HOURTIME);

		TextItem textItemProperties = new TextItem();
		textItemProperties.setHeight(20);
		textItemProperties.setWidth("*");
		textItemProperties.setTextBoxStyle("input");
		setTextFieldProperties(textItemProperties);

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
	public GHATimeItem(String title, ChangedHandler changedHandler) {
		this();
		setTitle(title);
		addChangedHandler(changedHandler);
	}

	public LogicalTime getValueAsLogicalTime() {
		Object value = getValue();
		if (value != null)
			return (LogicalTime) value;

		return null;
	}
}
