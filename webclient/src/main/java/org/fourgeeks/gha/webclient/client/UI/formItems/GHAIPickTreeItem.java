/**
 * 
 */
package org.fourgeeks.gha.webclient.client.UI.formItems;

import com.smartgwt.client.widgets.form.fields.IPickTreeItem;

/**
 * @author emiliot
 * 
 */
public class GHAIPickTreeItem extends IPickTreeItem {

	/**
	 * 
	 */
	public GHAIPickTreeItem() {
		super();
		setHeight(50);
		setWidth("*");
		setOriginalStyle();
		//		final Canvas buttonDefaults = Canvas.createIfSupported();
	}

	/**
	 * @param name
	 */
	public GHAIPickTreeItem(String name) {
		this();
		this.setName(name);
	}

	/**
	 * @param name
	 * @param title
	 */
	public GHAIPickTreeItem(String name, String title) {
		this();
		this.setTitle(title);
		this.setName(name);
	}

	/**
	 * 
	 */
	private void setOriginalStyle() {
		setTextBoxStyle("pickTreeItem");
		setCellStyle("gha-form-cell");
		setTitleStyle("input-title");
	}

}