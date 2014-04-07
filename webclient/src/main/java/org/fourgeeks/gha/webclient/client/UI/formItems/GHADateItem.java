package org.fourgeeks.gha.webclient.client.UI.formItems;

import java.util.Date;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.util.DateUtil;
import com.smartgwt.client.util.LogicalDate;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.validator.CustomValidator;
import com.smartgwt.client.widgets.form.validator.DateRangeValidator;

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
		setOriginalStyle();

		setDisplayFormat(DateDisplayFormat.TOEUROPEANSHORTDATE);
		setHoverWidth(100);
		setUseMask(true);
		// CustomValidator customValidator = getStandardValidator();
		// setValidators(customValidator);
		// setValidateOnChange(true);
		// addChangedHandler(new ChangedHandler() {
		// @Override
		// public void onChanged(ChangedEvent event) {
		// validate();
		// }
		// });
	}

	/**
	 * 
	 */
	private void setOriginalStyle() {
		setTextBoxStyle("dateItem");
		setCellStyle("gha-form-cell");
		setTitleStyle("input-title");
	}

	protected static CustomValidator getStandardValidator() {
		CustomValidator customValidator = new CustomValidator() {
			@Override
			protected boolean condition(Object value) {
				DateTimeFormat dtf = DateTimeFormat.getFormat("dd/MM/yyyy");
				try {
					Window.alert(dtf.parseStrict(value.toString()) + "");
					// dtf.parseStrict(value.toString());
					return true;
				} catch (IllegalArgumentException e) {
					return false;
				}
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

	/**
	 * @param title
	 * @param active
	 */
	public GHADateItem(String title, boolean active) {
		this(title);
		setDisabled(!active);
	}

	/**
	 * @param title
	 */
	public GHADateItem(String title, ChangedHandler changedHandler) {
		this();
		setTitle(title);
		addChangedHandler(changedHandler);

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
		Date value = (Date) getValue();
		if (value != null)
			return DateUtil.getLogicalDateOnly(value);

		return null;
	}

	@Override
	public void setRequired(Boolean required) {
		super.setRequired(required);
		if (required) {
			setTextBoxStyle("input required");
		} else {
			setOriginalStyle();
		}
		setShowErrorIcon(false);
	}

	public DateRangeValidator getValidator() {
		final DateRangeValidator drv = new DateRangeValidator();
		drv.setMin(new java.util.Date(0));
		final long currentTimeMillis = System.currentTimeMillis();
		final Date endDate = new Date(currentTimeMillis);
		drv.setMax(endDate);
		drv.setErrorMessage(GHAStrings.get("date-must-be-lower-than-today"));
		drv.setValidateOnChange(true);

		return drv;
	}

}