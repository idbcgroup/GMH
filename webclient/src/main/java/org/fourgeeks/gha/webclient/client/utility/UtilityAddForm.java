package org.fourgeeks.gha.webclient.client.utility;

import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.webclient.client.material.MaterialAddForm;

/**
 * @author alacret
 * 
 */
public class UtilityAddForm extends MaterialAddForm {

	/**
	 * @param title
	 */
	public UtilityAddForm(String title) {
		super(title);
		form.setType(MaterialTypeEnum.UTILITARIO);
	}

}
