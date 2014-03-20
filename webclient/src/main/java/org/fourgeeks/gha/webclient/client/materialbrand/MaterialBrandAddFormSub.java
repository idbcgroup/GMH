/**
 * 
 */
package org.fourgeeks.gha.webclient.client.materialbrand;

import org.fourgeeks.gha.domain.glm.MaterialTypeEnum;

/**
 * @author emiliot
 * 
 */
public class MaterialBrandAddFormSub extends MaterialBrandAddForm {
	public MaterialBrandAddFormSub(String title) {
		super(title);
		((MaterialBrandSetType) form).setType(MaterialTypeEnum.MATERIAL);
	}
}
