/**
 * 
 */
package org.fourgeeks.gha.webclient.client.materialbrand;

import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;

/**
 * @author emiliot
 * 
 */
public class MaterialBrandSearchFormSub extends MaterialBrandSearchForm {

	/**
	 * @param title
	 */
	public MaterialBrandSearchFormSub(String title) {
		super(title);
		fixedMaterial = MaterialTypeEnum.MATERIAL;
	}

}
