package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

/**
 * @author emiliot A select item with the eia state items
 * 
 */
public class GHAGenderSelectItem extends GHASelectItem {

	/**
	 * 
	 */
	public GHAGenderSelectItem() {
		super(GHAStrings.get("genre"));
		setValueMap(GenderTypeEnum.toValueMap());
	}

	/**
	 * @param width
	 */
	public GHAGenderSelectItem(int width) {
		this();
		setWidth(width);
	}

}
