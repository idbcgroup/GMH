package org.fourgeeks.gha.webclient.client.UI.formItems;

import java.util.Date;

import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.EditorExitEvent;
import com.smartgwt.client.widgets.form.fields.events.EditorExitHandler;

/**
 * @author alacret
 * 
 */
public class GHASelectItem extends SelectItem {

	/**
	 * 
	 */
	public GHASelectItem() {
		super();
		setHeight(20);
		setWidth("*");
		setPickerIconHeight(20);
		
		setOriginalStyle();
		setShowFocused(false);
		setAllowEmptyValue(true);

		// setErrorOrientation(FormErrorOrientation.RIGHT);
		setShowErrorIcon(false);
		setValidateOnExit(true);

		addEditorExitHandler(new EditorExitHandler() {
			@Override
			public void onEditorExit(EditorExitEvent event) {
				// Window.alert("Se dispara el EditorExit");
				if (getRequired()) {
					if (validate()) {
						setTextBoxStyle("select requiredValidated");
					} else {
						setTextBoxStyle("select required");
					}
				} else {
					setOriginalStyle();
				}
			}
		});
	}

	/**
	 * 
	 */
	private void setOriginalStyle() {
		setTextBoxStyle("select");
		setCellStyle("gha-form-cell");
		setTitleStyle("input-title");
	}

	/**
	 * @param width
	 */
	public GHASelectItem(int width) {
		this();
		setShowTitle(false);
		setWidth(width);
	}

	/**
	 * @param title
	 */
	public GHASelectItem(String title) {
		this();
		setTitle(title);
	}
	
	/**
	 * @param title
	 * @param width
	 */
	public GHASelectItem(String title, int width) {
		this(title);
		setWidth(width);
	}

	/**
	 * @param title
	 * @param width
	 * @param changedHandler
	 */
	public GHASelectItem(String title, int width, boolean required,
			ChangedHandler changedHandler) {
		this(title,width);
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param title
	 * @param changedHandler
	 */
	public GHASelectItem(String title, boolean required,
			ChangedHandler changedHandler) {
		this(title);
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param title
	 * @param width
	 * @param active
	 */
	public GHASelectItem(String title, int width, boolean active) {
		this(title);
		setWidth(width);
		setDisabled(!active);
	}

		/**
	 * @param title
	 * @param enabled
	 */
	public GHASelectItem(String title, boolean enabled) {
		this(title);
		setDisabled(!enabled);
	}

	@Override
	public void setDisabled(Boolean disabled) {
		super.setDisabled(disabled);
	}

	@Override
	public void setRequired(Boolean required) {
		super.setRequired(required);
		if (required) {
			setTextBoxStyle("select required");
		} else {
			setOriginalStyle();
		}
	}

	private void initialValidation() {
		Boolean required = getRequired();
		if (required != null && required) {
			if (validate()) {
				setTextBoxStyle("select requiredValidated");
				;
			} else {
				setTextBoxStyle("select required");
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
			setTextBoxStyle("select required");

		// setOriginalNullValue(null);
	}
}