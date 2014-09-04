package org.fourgeeks.gha.webclient.client.materialcategory;

import org.fourgeeks.gha.domain.glm.ServicesResourceCategory;

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
	 * @param servicesResourceCategory
	 */
	public void notifyMaterialCategory(ServicesResourceCategory servicesResourceCategory);
}