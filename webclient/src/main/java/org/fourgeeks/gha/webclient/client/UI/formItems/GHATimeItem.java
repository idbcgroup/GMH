package org.fourgeeks.gha.webclient.client.UI.formItems;

import java.util.Date;

import com.smartgwt.client.types.TimeDisplayFormat;
import com.smartgwt.client.util.DateUtil;
import com.smartgwt.client.util.LogicalTime;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.TimeItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;

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
		Date value = (Date) getValue();
		if (value != null)
			return DateUtil.getLogicalTimeOnly(value);

		return null;
	}

	public void setValidateTimer() {

		RegExpValidator textValidator = new RegExpValidator();
		textValidator.setErrorMessage("El Tiempo Introducido es Erroneo");
		textValidator.setExpression("([01]?[0-9]|2[0-3]):[0-5][0-9]");
		setValidators(textValidator);
		setShowErrorIcon(true);
		setValidateOnChange(true);
	}
}
