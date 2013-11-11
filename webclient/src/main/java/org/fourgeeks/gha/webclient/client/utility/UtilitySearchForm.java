package org.fourgeeks.gha.webclient.client.utility;

import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.webclient.client.materialcategory.MaterialCategorySearchForm;

/**
 * @author alacret
 * 
 */
public class UtilitySearchForm extends MaterialCategorySearchForm {

	/**
	 * @param title
	 * 
	 */
	public UtilitySearchForm(String title) {
		super(title);
		typeSelectItem.setValue(MaterialTypeEnum.UTILITARIO.name());
		typeSelectItem.setDisabled(true);
	}

	@Override
	public void clean() {
		super.clean();
		typeSelectItem.setValue(MaterialTypeEnum.UTILITARIO.name());
	}
}