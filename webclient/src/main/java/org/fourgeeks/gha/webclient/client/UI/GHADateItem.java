package org.fourgeeks.gha.webclient.client.UI;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.form.fields.DateItem;

/**
 * @author alacret TODO
 */
public class GHADateItem extends DateItem {
	
	/**
	 * TODO
	 */
	public GHADateItem() {
		super();
		setUseTextField(true);
		setTextBoxStyle("dateItem");
		setTextAlign(Alignment.LEFT);
		setHeight(18);
		setCellStyle("gha-form-cell");
		setWidth(100);
		setTitleStyle("input-title");
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
	
	public GHADateItem(String title, int width) {
		this(title);
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