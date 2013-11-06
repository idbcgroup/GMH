package org.fourgeeks.gha.webclient.client.UI.formItems;

import org.fourgeeks.gha.domain.enu.GenderTypeEnum;
import org.fourgeeks.gha.webclient.client.UI.GHAStrings;

/**
 * @author emiliot A select item with the eia state items
 * 
 */
public class GHAGenreSelectItem extends GHASelectItem {

	/**
	 * @param width
	 */
	public GHAGenreSelectItem(int width) {
		super(GHAStrings.get("genre"), width);
		setValueMap(GenderTypeEnum.toValueMap());
	}

}
