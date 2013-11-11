package org.fourgeeks.gha.webclient.client.material;

import org.fourgeeks.gha.domain.glm.Material;

/**
 * @author emiliot
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

	/**
	 * @param material
	 */
	public void notifyMaterial(Material material);
}
