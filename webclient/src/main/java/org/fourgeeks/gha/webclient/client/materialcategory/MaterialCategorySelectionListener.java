package org.fourgeeks.gha.webclient.client.materialcategory;

import org.fourgeeks.gha.domain.glm.MaterialCategory;

/**
 * @author alacret
 * 
 */
public interface MaterialCategorySelectionListener {
	/**
	 * @param material
	 *            triggers when a material is selected
	 */
	public void select(MaterialCategory material);
}