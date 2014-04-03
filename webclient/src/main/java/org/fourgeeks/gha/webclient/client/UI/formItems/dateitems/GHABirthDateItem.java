package org.fourgeeks.gha.webclient.client.UI.formItems.dateitems;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHADateItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

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

}
