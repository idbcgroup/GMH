package org.fourgeeks.gha.webclient.client.material;

import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;

/**
 * @author emiliot
 * 
 */
public class MaterialSearchFormSub extends MaterialSearchForm {

	/**
	 * @param title
	 */
	public MaterialSearchFormSub(String title) {
		super(title);
		fixedMaterial = MaterialTypeEnum.MATERIAL;
	}

}
