package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

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
		setTextBoxStyle("input");
		setCellStyle("gha-form-cell");
		setWidth(GHAUiHelper.DEFAULT_ITEM_SIZE);
		setTitleStyle("input-title");
		setLength(255);
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
		if (required)
			setTextBoxStyle("input required");
		else
			setTextBoxStyle("input");
	}
}