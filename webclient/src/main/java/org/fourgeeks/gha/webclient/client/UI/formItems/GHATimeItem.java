package org.fourgeeks.gha.webclient.client.UI.formItems;

import java.util.Date;

import com.smartgwt.client.widgets.form.fields.TimeItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

public class GHATimeItem extends TimeItem {

	public GHATimeItem() {
		super();
		setHeight(20);
		setOriginalStyle();
		setWidth("*");
		setMask("##:##");
	}

	private void setOriginalStyle() {
		setTextBoxStyle("input");
		setCellStyle("gha-form-cell");
		setTitleStyle("input-title");
	}

	/**
	 * @param title
	 * @param width
	 */
	public GHATimeItem(String title, int width) {
		this(title);
		setWidth(width);
	}

	/**
	 * @param title
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHATimeItem(String title, int width, boolean required,
			ChangedHandler changedHandler) {
		this(title, width);
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param title
	 * @param required
	 * @param changedHandler
	 */
	public GHATimeItem(String title, boolean required,
			ChangedHandler changedHandler) {
		this(title);
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param width
	 */
	public GHATimeItem(int width) {
		this();
		setShowTitle(false);
		setWidth(width);
	}

	/**
	 * @param title
	 * @param width
	 * @param active
	 */
	public GHATimeItem(String title, int width, boolean active) {
		this(title, width);
		setDisabled(!active);
	}

	/**
	 * @param title
	 */
	public GHATimeItem(String title) {
		this();
		setTitle(title);
	}

	/**
	 * @param title
	 * @param active
	 */
	public GHATimeItem(String title, boolean active) {
		this(title);
		setDisabled(!active);
	}

	@Override
	public void setDisabled(Boolean disabled) {
		super.setDisabled(disabled);
	}

	@Override
	public void setRequired(Boolean required) {
		super.setRequired(required);
		if (required) {
			setTextBoxStyle("input required");
		} else {
			setOriginalStyle();
		}
	}

	private void initialValidation() {
		Boolean required = getRequired();
		if (required != null && required) {
			if (validate()) {
				setTextBoxStyle("input requiredValidated");
				;
			} else {
				setTextBoxStyle("input required");
			}
		}
	}

	@Override
	public void setValue(boolean value) {
		super.setValue(value);
		initialValidation();
	}

	@Override
	public void setValue(String value) {
		super.setValue(value);
		initialValidation();
	}

	@Override
	public void setValue(Date value) {
		super.setValue(value);
		initialValidation();
	}

	@Override
	public void setValue(int value) {
		super.setValue(value);
		initialValidation();
	}

	@Override
	public void setValue(double value) {
		super.setValue(value);
		initialValidation();
	}

	@Override
	public void setValue(Object value) {
		super.setValue(value);
		initialValidation();
	}

	@Override
	public void clearValue() {
		super.clearValue();
		Boolean required = getRequired();
		setOriginalStyle();
		if (required != null && required)
			setTextBoxStyle("input required");
	}
}
