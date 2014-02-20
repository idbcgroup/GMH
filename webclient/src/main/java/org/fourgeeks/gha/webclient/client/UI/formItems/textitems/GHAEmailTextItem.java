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
	 */
	public GHAEmailTextItem() {
		super(GHAStrings.get("mail"));
		initEmailValidator();
		setShowErrorIcon(true);
	}

	/**
	 * Creates a email text item with a regExpValidator for email fields.
	 * 
	 * @param title
	 */
	public GHAEmailTextItem(String title) {
		super(title);
		initEmailValidator();
		setShowErrorIcon(true);
	}

	/**
	 * Creates a email text item with a regExpValidator for email fields.
	 * 
	 * @param width
	 */
	public GHAEmailTextItem(int width) {
		this();
		setWidth(width);
	}

	/**
	 * Creates a email text item with a regExpValidator for email fields.
	 * 
	 * @param title
	 * @param active
	 */
	public GHAEmailTextItem(String title, boolean active) {
		this(title);
		setDisabled(!active);
	}

	/**
	 * Creates a email text item with a regExpValidator for email fields.
	 * 
	 * @param title
	 * @param width
	 */
	public GHAEmailTextItem(String title, int width) {
		this(title);
		setWidth(width);
	}

	/**
	 * Creates a email text item with a regExpValidator for email fields.
	 * 
	 * @param title
	 * @param width
	 * @param active
	 */
	public GHAEmailTextItem(String title, int width, boolean active) {
		this(title, active);
		setWidth(width);
	}

	/**
	 * Creates a email text item with a regExpValidator for email fields.
	 * 
	 * @param title
	 */
	public GHAEmailTextItem(String title, ChangedHandler chengedHandler) {
		this(title);
		addChangedHandler(chengedHandler);
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
