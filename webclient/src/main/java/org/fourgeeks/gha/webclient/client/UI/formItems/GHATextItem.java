package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.EditorExitEvent;
import com.smartgwt.client.widgets.form.fields.events.EditorExitHandler;

/**
 * @author alacret
 * 
 */
public class GHATextItem extends TextItem {

	/**
	 * 
	 */
	public GHATextItem() {
		super();
		setHeight(20);
		setOriginalStyle();
		setWidth(GHAUiHelper.DEFAULT_ITEM_SIZE);
		setLength(255);
	}

	/**
	 * 
	 */
	private void setOriginalStyle() {
		setTextBoxStyle("input");
		setCellStyle("gha-form-cell");
		setTitleStyle("input-title");
	}

	/**
	 * @param title
	 * @param width
	 */
	public GHATextItem(String title, int width) {
		this(title);
		setWidth(width);
	}

	/**
	 * @param title
	 * @param width
	 * @param required
	 * @param changedHandler
	 */
	public GHATextItem(String title, int width, boolean required,
			ChangedHandler changedHandler) {
		this(title, width);
		setRequired(required);
		addChangedHandler(changedHandler);
	}

	/**
	 * @param width
	 */
	public GHATextItem(int width) {
		this();
		setShowTitle(false);
		setWidth(width);
	}

	/**
	 * @param title
	 * @param width
	 * @param active
	 */
	public GHATextItem(String title, int width, boolean active) {
		this(title, width);
		setDisabled(!active);
	}

	/**
	 * @param title
	 */
	public GHATextItem(String title) {
		this();
		setTitle(title);
	}

	/**
	 * @param title
	 * @param active
	 */
	public GHATextItem(String title, boolean active) {
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
			// setErrorOrientation(FormErrorOrientation.RIGHT);
			setShowErrorIcon(false);
			setValidateOnExit(true);
			addEditorExitHandler(new EditorExitHandler() {
				@Override
				public void onEditorExit(EditorExitEvent event) {
					if (validate()) {
						setTextBoxStyle("input requiredValidated");
					} else {
						setTextBoxStyle("input required");
					}
				}
			});
			// validate();
		} else {
			setTextBoxStyle("input");
		}
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