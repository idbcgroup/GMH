package org.fourgeeks.gha.webclient.client.UI.formItems;

import java.util.Date;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.validator.DateRangeValidator;

/**
 * @author alacret
 */
public class GHABirthDateItem extends GHADateItem {

	/**
	 * 
	 */
	public GHABirthDateItem(String title, ChangedHandler changeHandler) {
		super(title);
		addChangedHandler(changeHandler);
		DateRangeValidator drv = new DateRangeValidator();
		drv.setMin(new java.util.Date(0));
		long currentTimeMillis = System.currentTimeMillis();
		Date endDate = new Date(currentTimeMillis);
		drv.setMax(endDate);
		setValidators(drv);
		drv.setValidateOnChange(true);
		setValidateOnChange(true);
		drv.setErrorMessage(GHAStrings.get("date-must-be-lower-than-today"));
		addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				GHABirthDateItem.this.validate();
			}
		});
	}

}