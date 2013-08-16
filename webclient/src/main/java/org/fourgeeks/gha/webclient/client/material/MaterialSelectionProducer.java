package org.fourgeeks.gha.webclient.client.material;


/**
 * @author alacret
 * 
 */
public interface MaterialSelectionProducer {
	/**
	 * @param materialSelectionListener
	 */
	public void addMaterialSelectionListener(
			MaterialSelectionListener materialSelectionListener);

	/**
	 * @param materialSelectionListener
	 */
	public void removeMaterialSelectionListener(
			MaterialSelectionListener materialSelectionListener);
}