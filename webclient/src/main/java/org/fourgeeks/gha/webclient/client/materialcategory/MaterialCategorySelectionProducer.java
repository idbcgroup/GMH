package org.fourgeeks.gha.webclient.client.materialcategory;


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
}