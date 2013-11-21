package org.fourgeeks.gha.webclient.client.UI.formItems;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;

/**
 * @author alacret TODO
 */
public class GHADateItem extends DateItem {

	/**
	 * TODO
	 */
	public GHADateItem() {
		super();
		setHeight(20);
		setWidth("*");
		setUseTextField(true);
		setTextAlign(Alignment.LEFT);
		setTextBoxStyle("dateItem");
		setCellStyle("gha-form-cell");
		setTitleStyle("input-title");
		setDisplayFormat(DateDisplayFormat.TOEUROPEANSHORTDATE);
	}

	/**
	 * TODO
	 * 
	 * @param title
	 */
	public GHADateItem(String title) {
		this();
		setTitle(title);
	}

	/**
	 * @param title
	 */
	public GHADateItem(String title, ChangedHandler changeHandler) {
		this();
		setTitle(title);
		addChangedHandler(changeHandler);
	}

	public GHADateItem(String title, int width) {
		this(title);
		setWidth(width);
	}

	public GHADateItem(int width) {
		this();
		setShowTitle(false);
		setWidth(width);
	}

	public GHADateItem(String title, int width, boolean active) {
		this(title);
		setWidth(width);
		setDisabled(!active);
	}

	public GHADateItem(String title, boolean active) {
		this(title);
		setDisabled(!active);
	}

	@Override
	public void setDisabled(Boolean disabled) {
		super.setDisabled(disabled);
	}

}