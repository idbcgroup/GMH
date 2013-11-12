package org.fourgeeks.gha.webclient.client.material;

import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;

/**
 * @author emiliot
 * 
 */
public class MaterialAddFormSub extends MaterialAddForm {

	/**
	 * @param title
	 */
	public MaterialAddFormSub(String title) {
		super(title);
		form.setType(MaterialTypeEnum.MATERIAL);
	}

}
