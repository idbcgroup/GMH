/**
 * 
 */
package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.webclient.client.UI.GHAUiHelper;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.widgets.form.fields.IPickTreeItem;

/**
 * @author emiliot
 * 
 */
public class GHAIPickTreeItem extends IPickTreeItem {

	/**
	 * @param width TODO
	 * 
	 */
	public GHAIPickTreeItem(int width) {
		super();
		setHeight(20);
		setWidth(width);
		setOriginalStyle();
		//		final Canvas buttonDefaults = Canvas.createIfSupported();
	}

	/**
	 * @param name
	 * @param width TODO
	 */
	public GHAIPickTreeItem(String name, int width) {
		this(width);
		setName(name);
	}

	/**
	 * @param name
	 * @param title
	 * @param width TODO
	 */
	public GHAIPickTreeItem(String name, String title, int width) {
		this(name, width);
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
	public void resizeWidth(int width){
		if(width < GHAUiHelper.DEFAULT_ITEM_WIDTH){
			Window.alert("resize Default pick tree");
			setWidth(GHAUiHelper.DEFAULT_ITEM_WIDTH);
		}else{
			setWidth(width);
			Window.alert("resize custom size pick tree:"+width);
		}
	}
}