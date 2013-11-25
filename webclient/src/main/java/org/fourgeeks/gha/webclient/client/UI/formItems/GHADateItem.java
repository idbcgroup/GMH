package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.util.LogicalDate;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.validator.CustomValidator;

/**
 * @author alacret TODO
 */
public class GHADateItem extends DateItem {

	/**
	 * TODO
	 */
	public GHADateItem() {
		super();
		setHeight(20);
		setWidth("*");
		setUseTextField(true);
		setTextAlign(Alignment.LEFT);
		setTextBoxStyle("dateItem");
		setCellStyle("gha-form-cell");
		setTitleStyle("input-title");
		setDisplayFormat(DateDisplayFormat.TOEUROPEANSHORTDATE);
		CustomValidator customValidator = getStandardValidator();
		setValidators(customValidator);
		addChangedHandler(new ChangedHandler() {
			@Override
			public void onChanged(ChangedEvent event) {
				validate();
			}
		});
	}

	protected static CustomValidator getStandardValidator() {
		CustomValidator customValidator = new CustomValidator() {
			@Override
			protected boolean condition(Object value) {
				DateTimeFormat dtf = DateTimeFormat.getFormat("dd/MM/yyyy");
				try {
					dtf.parseStrict(value.toString());
					return true;
				} catch (IllegalArgumentException e) {
					return false;
				}
//				Window.alert("after trycatch");
//				return false;
			}
		};
		customValidator.setErrorMessage(GHAStrings.get("date-not-valid"));
		return customValidator;
	}

	public GHADateItem(int width) {
		this();
		setShowTitle(false);
		setWidth(width);
	}

	/**
	 * TODO
	 * 
	 * @param title
	 */
	public GHADateItem(String title) {
		this();
		setTitle(title);
	}

	public GHADateItem(String title, boolean active) {
		this(title);
		setDisabled(!active);
	}

	/**
	 * @param title
	 */
	public GHADateItem(String title, ChangedHandler changeHandler) {
		this();
		setTitle(title);
		addChangedHandler(changeHandler);
	}

	public GHADateItem(String title, int width) {
		this(title);
		setWidth(width);
	}

	public GHADateItem(String title, int width, boolean active) {
		this(title);
		setWidth(width);
		setDisabled(!active);
	}

	@Override
	public void setDisabled(Boolean disabled) {
		super.setDisabled(disabled);
	}

	public LogicalDate getValueAsLogicalDate() {
		Object value = getValue();
		if (value != null)
			return (LogicalDate) value;
		return null;
	}

}