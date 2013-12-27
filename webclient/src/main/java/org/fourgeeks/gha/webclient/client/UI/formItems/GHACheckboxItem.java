package org.fourgeeks.gha.webclient.client.UI.formItems;

import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author naramirez
 */
public class GHACheckboxItem extends CheckboxItem {

	/**
	 * create a Checkbox
	 */
	public GHACheckboxItem() {
		super();
		setHeight(20);
		setWidth("*");
		setPickerIconHeight(20);

		setShowTitle(false);
		setTextBoxStyle("checkbox");

		// setErrorOrientation(FormErrorOrientation.RIGHT);
		setShowErrorIcon(false);
	}

	/**
	 * @param title
	 *            User visible title for this checkbox
	 */
	public GHACheckboxItem(String title) {
		this();
		setTitle(title);
	}

	/**
	 * @param title
	 *            User visible title for this checkbox
	 * @param active
	 *            true if this item should be active
	 */
	public GHACheckboxItem(String title, boolean active) {
		this(title);
		setDisabled(!active);
	}

	/**
	 * @param title
	 *            User visible title for this checkbox
	 * @param active
	 *            true if this item should be active
	 * @param changedHandler
	 *            the changed handler
	 */
	public GHACheckboxItem(String title, boolean active,
			ChangedHandler changedHandler) {
		this(title, active);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param title
	 *            User visible title for this checkbox
	 * @param changedHandler
	 *            the changed handler
	 */
	public GHACheckboxItem(String title, ChangedHandler changedHandler) {
		this(title);
		addChangedHandler(changedHandler);
	}
}