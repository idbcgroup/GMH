package org.fourgeeks.gha.webclient.client.UI.formItems;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;

/**
 * @author alacret
 * 
 */
public class GHATitletextItem extends StaticTextItem {

	/**
	 * 
	 */
	public GHATitletextItem() {
		super();
		setAlign(Alignment.LEFT);
		setVAlign(VerticalAlignment.BOTTOM);
		setHeight(20);
		setWidth("*");
		setCellStyle("gha-form-cell");
		setShowTitle(false);
		setTextBoxStyle("title-label");
	}

	/**
	 * @param title
	 */
	public GHATitletextItem(String title) {
		this();
		setDefaultValue(title);
	}
	
	/**
	 * @param title
	 * @param colSpan
	 */
	public GHATitletextItem(String title, int colSpan) {
		this();
		setDefaultValue(title);
		setColSpan(colSpan);
	}
}