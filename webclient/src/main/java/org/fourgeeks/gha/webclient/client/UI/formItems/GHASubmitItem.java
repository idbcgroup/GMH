package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.form.fields.SubmitItem;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

public class GHASubmitItem extends SubmitItem {
	public GHASubmitItem() {
		super();
//		setTextBoxStyle("input");
		setHeight(20);
		setCellStyle("gha-form-cell");
		setWidth(GHAUiHelper.DEFAULT_ITEM_SIZE);
//		setTitleStyle("input-title");
	}

	public GHASubmitItem(String title, int width) {
		this(title);
		setWidth(width);
	}
	
	public GHASubmitItem(String title, int width, boolean active) {
		this(title);
		setWidth(width);
		setDisabled(!active);
	}

	public GHASubmitItem(String title) {
		this();
		setTitle(title);
	}
	
	public GHASubmitItem(String title, ClickHandler ch) {
		this(title);
		addClickHandler(ch);
	}

	public GHASubmitItem(String title, boolean active) {
		this(title);
		setDisabled(!active);
	}

	@Override
	public void setDisabled(Boolean disabled) {
		super.setDisabled(disabled);
	}
}
