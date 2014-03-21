/**
 * 
 */
package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.smartgwt.client.widgets.form.fields.IPickTreeItem;

/**
 * @author emiliot
 * 
 */
public class GHAPickTreeItem extends IPickTreeItem {

	/**
	 * 
	 */
	public GHAPickTreeItem() {
		super();
		setHeight(20);
		setWidth(GHAUiHelper.DEFAULT_ITEM_WIDTH);
		setOriginalStyle();
		//		final Canvas buttonDefaults = Canvas.createIfSupported();
	}

	/**
	 * @param name
	 */
	public GHAPickTreeItem(String name) {
		this();
		setName(name);
	}

	/**
	 * @param name
	 * @param title
	 */
	public GHAPickTreeItem(String name, String title) {
		this(name);
		setTitle(title);
	}

	/**
	 * 
	 */
	private void setOriginalStyle() {

		setTextBoxStyle("pickTreeItem");
		setCellStyle("gha-form-cell");
		setTitleStyle("input-title");
	}

	@Override
	public void setRequired(Boolean required) {
		super.setRequired(required);
		if (required) {

			setTextBoxStyle("pickTreeItem required");
		} else {
			setOriginalStyle();
		}
	}

	/**
	 * @param width
	 */
	@Deprecated
	public void resizeWidth(int width){
		if(width < GHAUiHelper.DEFAULT_ITEM_WIDTH){
			setWidth(GHAUiHelper.DEFAULT_ITEM_WIDTH);
		}else{
			setWidth(width);
		}
		redraw();
	}
}