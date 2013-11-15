package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;

/**
 * @author emiliot
 * 
 */
public class GHAEmailItem extends GHATextItem {

	/**
	 * Creates a email text item with a regExpValidator for email fields.
	 * 
	 * @param width
	 */
	public GHAEmailItem(int width) {
		super(width);
		initEmailValidator();
	}

	/**
	 * Creates a email text item with a regExpValidator for email fields.
	 * 
	 * @param title
	 * @param active
	 */
	public GHAEmailItem(String title, boolean active) {
		super(title, active);
		initEmailValidator();
	}

	/**
	 * Creates a email text item with a regExpValidator for email fields.
	 * 
	 * @param title
	 * @param width
	 * @param active
	 */
	public GHAEmailItem(String title, int width, boolean active) {
		super(title, width, active);
		initEmailValidator();
	}

	/**
	 * Creates a email text item with a regExpValidator for email fields.
	 * 
	 * @param title
	 * @param width
	 */
	public GHAEmailItem(String title, int width) {
		super(title, width);
		initEmailValidator();
	}

	/**
	 * Creates a email text item with a regExpValidator for email fields.
	 * 
	 * @param title
	 */
	public GHAEmailItem(String title) {
		super(title);
		initEmailValidator();
	}

	/**
	 * Creates a email text item with a regExpValidator for email fields.
	 * 
	 * @param title
	 */
	public GHAEmailItem(String title, ChangedHandler chengedHandler) {
		super(title);
		addChangedHandler(chengedHandler);
		initEmailValidator();
	}

	/**
	 * Creates a email text item with a regExpValidator for email fields.
	 */
	public GHAEmailItem() {
		super();
		initEmailValidator();
	}

	private void initEmailValidator() {
		setLength(256);
		RegExpValidator emailValidator = new RegExpValidator();
		emailValidator.setErrorMessage(GHAStrings.get("email-invalid-field"));
		emailValidator
				.setExpression("^([a-zA-Z0-9_.\\-+])+@(([a-zA-Z0-9\\-])+\\.)+[a-zA-Z0-9]{2,4}$");
		this.setValidators(emailValidator);
	}

}
