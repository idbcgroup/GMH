package org.fourgeeks.gha.webclient.client.utility;

import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.webclient.client.materialbrand.MaterialBrandSearchForm;

/**
 * @author alacret
 * 
 */
public class UtilitySearchForm extends MaterialBrandSearchForm {

	/**
	 * @param title
	 * 
	 */
	public UtilitySearchForm(String title) {
		super(title);
		fixedMaterial = MaterialTypeEnum.UTILITARIO;
	}
}
