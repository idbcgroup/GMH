package org.fourgeeks.gha.webclient.client.UI.formItems.dateitems;

import java.util.Date;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.validator.DateRangeValidator;

/**
 * @author alacret
 */
public class GHABirthDateItem extends GHADateItem {

	/**
	 * 
	 */
	public GHABirthDateItem() {
		super(GHAStrings.get("birthdate"));
		setValidateOnChange(true);
		setHoverWidth(150);

		setValidators(getValidator());

	}

	/**
	 * 
	 */
	public GHABirthDateItem(ChangedHandler changeHandler) {
		this();
		addChangedHandler(changeHandler);

		setValidateOnChange(true);
		setHoverWidth(150);

		setValidators(getValidator());
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
