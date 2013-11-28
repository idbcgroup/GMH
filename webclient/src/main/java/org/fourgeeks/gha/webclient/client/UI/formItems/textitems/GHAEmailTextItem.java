package org.fourgeeks.gha.webclient.client.UI.formItems.textitems;

import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHATextItem;

import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.validator.RegExpValidator;

/**
 * @author emiliot
 * 
 */
public class GHAEmailTextItem extends GHATextItem {

	/**
	 * Creates a email text item with a regExpValidator for email fields.
	 * 
	 * @param width
	 */
	public GHAEmailTextItem(int width) {
		super(width);
		initEmailValidator();
	}

	/**
	 * Creates a email text item with a regExpValidator for email fields.
	 * 
	 * @param title
	 * @param active
	 */
	public GHAEmailTextItem(String title, boolean active) {
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
	public GHAEmailTextItem(String title, int width, boolean active) {
		super(title, width, active);
		initEmailValidator();
	}

	/**
	 * Creates a email text item with a regExpValidator for email fields.
	 * 
	 * @param title
	 * @param width
	 */
	public GHAEmailTextItem(String title, int width) {
		super(title, width);
		initEmailValidator();
	}

	/**
	 * Creates a email text item with a regExpValidator for email fields.
	 * 
	 * @param title
	 */
	public GHAEmailTextItem(String title) {
		super(title);
		initEmailValidator();
	}

	/**
	 * Creates a email text item with a regExpValidator for email fields.
	 * 
	 * @param title
	 */
	public GHAEmailTextItem(String title, ChangedHandler chengedHandler) {
		super(title);
		addChangedHandler(chengedHandler);
		initEmailValidator();
	}

	/**
	 * Creates a email text item with a regExpValidator for email fields.
	 */
	public GHAEmailTextItem() {
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
