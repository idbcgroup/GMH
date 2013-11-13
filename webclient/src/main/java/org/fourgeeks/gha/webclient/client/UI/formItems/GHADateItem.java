package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

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
		setHeight(20);
		setWidth("*");
		setUseTextField(true);
		setTextAlign(Alignment.LEFT);
		setTextBoxStyle("dateItem");
		setCellStyle("gha-form-cell");
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