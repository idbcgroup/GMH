package org.fourgeeks.gha.webclient.client.utility;

import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.webclient.client.materialbrand.MaterialBrandAddForm;
import org.fourgeeks.gha.webclient.client.materialbrand.MaterialBrandSetType;

/**
 * @author alacret
 * 
 */
public class UtilityAddForm extends MaterialBrandAddForm {

	/**
	 * @param title
	 */
	public UtilityAddForm(String title) {
		super(title);
		((MaterialBrandSetType) form).setType(MaterialTypeEnum.UTILITARIO);
	}

}
