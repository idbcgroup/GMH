package org.fourgeeks.gha.webclient.client.UI.formItems.selectitems;

import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;
import org.fourgeeks.gha.webclient.client.UI.formItems.GHASelectItem;

/**
 * @author emiliot A select item with the eia state items
 * 
 */
public class GHAGenreSelectItem extends GHASelectItem {
	public static final String labelKey = "genre";
	/**
	 * 
	 */
	public GHAGenreSelectItem() {
		super(GHAStrings.get(labelKey));
		setValueMap(GenderTypeEnum.toValueMap());
	}
	
	/**
	 * @param width
	 */
	public GHAGenreSelectItem(int width) {
		super(GHAStrings.get(labelKey), width);
		setValueMap(GenderTypeEnum.toValueMap());
	}

}
