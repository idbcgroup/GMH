package org.fourgeeks.gha.webclient.client.eiatype.utility;

import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;
import org.fourgeeks.gha.webclient.client.material.MaterialSearchForm;

/**
 * @author alacret
 * 
 */
public class UtilitySearchForm extends MaterialSearchForm {

	public UtilitySearchForm() {
		super();
		typeSelectItem.setValue(MaterialTypeEnum.UTILITARIO.ordinal());
		typeSelectItem.setDisabled(true);
	}
}
