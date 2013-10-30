package org.fourgeeks.gha.webclient.client.materialcategory;

import org.fourgeeks.gha.domain.glm.MaterialCategory;

/**
 * @author alacret
 * 
 */
public interface MaterialCategorySelectionProducer {
	/**
	 * @param materialSelectionListener
	 */
	public void addMaterialSelectionListener(
			MaterialCategorySelectionListener materialSelectionListener);

	/**
	 * @param materialSelectionListener
	 */
	public void removeMaterialSelectionListener(
			MaterialCategorySelectionListener materialSelectionListener);

	/**
	 * 
	 * @param materialCategory
	 */
	public void notifyMaterialCategory(MaterialCategory materialCategory);
}